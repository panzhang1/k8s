package com.zp.k8s.client;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.api.model.batch.Job;
import io.fabric8.kubernetes.api.model.batch.JobCondition;
import io.fabric8.kubernetes.api.model.batch.JobStatus;
import io.fabric8.kubernetes.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class K8sClient {

    private static final Logger LOG = LoggerFactory.getLogger(K8sClient.class);
    private static final int RETRY_JOB_STATUS_COUNT = 5;
    private static final int ERROR_CODE_409 = 409;    //Job exist
    private String namespace;
    private KubernetesClient client;

    public K8sClient(KubernetesClient client,String k8sNameSpace) {
        this.client = client;
        this.namespace = k8sNameSpace;
    }


    public static void main(String[] args){
        K8sClient k8sClient = new K8sClient(new DefaultKubernetesClient(),"mcap");

        //test get service by label
        //List<String> serviceNames = k8sClient.getServiceName("tenant=TenantLifeCycle");
        //LOG.info("serviceNames:" + serviceNames.toString());

        k8sClient.watchJobs(2, "tenant=CreateTenant", new JobStatusCallbackMock());
    }

    public Job tryCreateJob(Job job) {
        Job jobCreated = null;
        try {
            jobCreated = createJob(job);
        } catch (KubernetesClientException e) {
            if (e.getCode() == ERROR_CODE_409) {
                LOG.info(String.format("job(%s) already exist, delete it first", job.getMetadata().getName()));
                boolean deleteResult = deleteJob(job);
                if (deleteResult) {
                    jobCreated = createJob(job);
                } else {
                    LOG.error(String.format("Can't delete job(%s)", job.getMetadata().getName()));
                }
            } else {
                LOG.error(String.format("Can't create job(%s)", job.getMetadata().getName()),e);
            }
        }
        return jobCreated;
    }

    public Job createJob(Job job) {
        Job result = client.batch().jobs().inNamespace(namespace).create(job);
        if (result != null) {
            LOG.info(String.format("Created job(%s)", result.getMetadata().getName()));
        }
        return result;
    }

    public Job getJobFromTemplateFile(String fileName) {
        List<HasMetadata> resources = null;
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        resources = client.load(is).get();
        if (resources.isEmpty()) {
            LOG.error(String.format("No resources loaded from file(%s)",fileName));
            return null;
        }

        HasMetadata resource = resources.get(0);
        if (resource instanceof Job) {
            return  (Job) resource;
        }
        return null;
    }

    public boolean deleteJob(Job job) {
        return client.batch().jobs().inNamespace(namespace).delete(job);
    }

    public boolean deleteJobs(List<Job> jobs) {
        return client.batch().jobs().inNamespace(namespace).delete(jobs);
    }


    public void watchJob(String jobName, JobStatusCallback statusCallback) {
        final CountDownLatch eventLatch = new CountDownLatch(1);
        try(Watch watch = client.batch().jobs().inNamespace(namespace).withName(jobName).watch(new Watcher<Job>() {
            @Override
            public void onClose(KubernetesClientException e) {
                LOG.error("watch closed", e);
            }

            @Override
            public void eventReceived(Action action, Job job) {
                LOG.info(action.name() + "," + job.getMetadata().getName());
                if (job.getStatus() != null) {
                    LOG.info("jobStatus:" + job.getStatus().getSucceeded() + "," + job.getStatus().getFailed());
                    if (job.getStatus().getSucceeded() != null && job.getStatus().getSucceeded() > 0) {
                        statusCallback.success(job.getMetadata().getName(),job.getStatus().getSucceeded());
                        eventLatch.countDown();
                    }
                    if (job.getStatus().getFailed() != null && job.getStatus().getFailed() > 0) {
                        statusCallback.failure(job.getMetadata().getName(),job.getStatus().getFailed());
                        eventLatch.countDown();
                    }
                } else {
                    LOG.info("No job status yet");
                }
            }
        })) {
            try {
                eventLatch.await(10, TimeUnit.MINUTES);
                watch.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void watchJobs(int jobCount, String label, JobStatusCallback statusCallback) {
        final CountDownLatch eventLatch = new CountDownLatch(jobCount);
        try(Watch watch = client.batch().jobs().inNamespace(namespace).withLabel(label).watch(new Watcher<Job>() {
            @Override
            public void onClose(KubernetesClientException e) {
                LOG.error("watch closed", e);
            }

            @Override
            public void eventReceived(Action action, Job job) {
                LOG.info(action.name() + "," + job.getMetadata().getName());
                if (job.getStatus() != null) {
                    if (job.getStatus().getSucceeded() != null && job.getStatus().getSucceeded() > 0) {
                        statusCallback.success(job.getMetadata().getName(),job.getStatus().getSucceeded());
                        eventLatch.countDown();
                    }
                    if (job.getStatus().getFailed() != null && job.getStatus().getFailed() > 0) {
                        statusCallback.failure(job.getMetadata().getName(),job.getStatus().getFailed());
                        eventLatch.countDown();
                        //TODO:
                    }
                }
            }
        })) {
            try {
                eventLatch.await(10, TimeUnit.MINUTES);
                watch.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public PullJobStatus pullJobStatus(String jobName) throws InterruptedException {
        PullJobStatus status = PullJobStatus.TIMEOUT;
        int retryCount = 0;
        while(retryCount <= RETRY_JOB_STATUS_COUNT) {
            retryCount ++;
            TimeUnit.SECONDS.sleep(60);
            Job job = getJob(jobName);
            if (job == null) {
                LOG.info(String.format("Pull Job(%s) but job does not exist", jobName));
                return PullJobStatus.FAIL;
            }
            JobStatus jobStatus = job.getStatus();
            if(jobStatus!= null && jobStatus.getSucceeded() != null &&  jobStatus.getSucceeded() > 0) {
                return PullJobStatus.SUCCESS;
            } else if (jobStatus != null && jobStatus.getFailed() != null &&  jobStatus.getFailed() > 0) {
                return PullJobStatus.FAIL;
            } else {
                LOG.info(String.format("Pull Job(%s) status in progress, retry %s", jobName,retryCount));
            }
        }
        LOG.error(String.format("Fail to get job(%s) status due to timeout", jobName));
        return status;
    }

    private Job getJob(String jobName) {
        Job job = client.batch().jobs().inNamespace(namespace).withName(jobName).get();
        if (job != null) {
            return job;
        } else {
            LOG.error(String.format("Fail to get Job(%s)", jobName));
            return null;
        }
    }

    public List<String> getServiceName(String tag) {
        List<String> serviceNames =  new ArrayList<>();
        ServiceList serviceList = client.services().inNamespace(namespace).withLabel(tag).list();
        if (serviceList != null) {
            for(Service service : serviceList.getItems()) {
                serviceNames.add(service.getMetadata().getName());
            }
        }
        return serviceNames;
    }
}
