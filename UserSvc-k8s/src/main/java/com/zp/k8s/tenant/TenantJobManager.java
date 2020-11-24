package com.zp.k8s.tenant;

import com.zp.k8s.client.JobStatusCallbackMock;
import com.zp.k8s.client.K8sClient;
import com.zp.k8s.client.PullJobStatus;
import io.fabric8.kubernetes.api.model.batch.Job;
import io.fabric8.kubernetes.api.model.batch.JobBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TenantJobManager {
    private static final String K8S_NAMESPACE = "mcap";
    private static final String template_job_createtenant = "create-tenant-job.yaml";
    private static final String ENV_PLATFORM_TLS_SERVERTYPE = "PLATFORM_TLS_SERVERTYPE";
    private static final String ENV_PLATFORM_TLS_TENANTID = "PLATFORM_TLS_TENANTID";
    private static final String PLATFORM_TLS_SERVERTYPE_JOB = "job";
    private static final Logger LOG = LoggerFactory.getLogger(TenantJobManager.class);

    public static void main(String[] args){
        TenantJobManager example = new TenantJobManager();
        TenantJobStatus status = example.submitJob("BizxTest");
        LOG.info("job status:" + status);
    }

    public TenantJobStatus submitJob(String tenantId) {
        List<Job> iniJobs = new ArrayList<>();
        List<String> serviceNames = new ArrayList<>();
        //Hardcoded first
        serviceNames.add("activity");
        serviceNames.add("user");
        //
        for (String serviceName : serviceNames) {
            String jobName = String.format("createTenant-%s-%s", serviceName,tenantId).toLowerCase();
            String containerUrl = String.format("repo-dev.successfactors.sap.corp/plumber/createtenant_%s:latest", serviceName);
            Job job = prepareJob(tenantId, jobName, jobName, containerUrl);
            iniJobs.add(job);
        }

        K8sClient k8sClient = new K8sClient(new DefaultKubernetesClient(), K8S_NAMESPACE);
        TenantJobStatus jobStatus = TenantJobStatus.SUCCESS;

        for(Job jobAdd : iniJobs) {
            Job jobCreated = k8sClient.tryCreateJob(jobAdd);
            if (jobCreated == null) {
                LOG.error(String.format("submitJob flow fail to create job(%s)", jobAdd.getMetadata().getName()));
                return TenantJobStatus.CREATE_FAIL;
            }
        }

        k8sClient.watchJobs(iniJobs.size(), getCreateTenantLable(), new JobStatusCallbackMock());

        boolean deleteStatus = k8sClient.deleteJobs(iniJobs);
        LOG.info(String.format("delete job status(%s)", deleteStatus));
        if (!deleteStatus) {
            jobStatus = TenantJobStatus.DELETE_FAIL;
        }
        return jobStatus;
    }

    private String getCreateTenantLable() {
        return "tenant=CreateTenant";
    }

    /*
    private TenantJobStatus submitJobAndMonitorByPull(Job jobTemplate) {
        K8sClient k8sClient = new K8sClient(K8S_NAMESPACE);
        String jobName = jobTemplate.getMetadata().getName();
        TenantJobStatus jobStatus = TenantJobStatus.SUCCESS;

        Job jobCreated = k8sClient.tryCreateJob(jobTemplate);
        if (jobCreated == null) {
            LOG.error(String.format("submitJob flow fail to create job(%s)", jobName));
            return TenantJobStatus.CREATE_FAIL;
        }

        try {
            TimeUnit.SECONDS.sleep(120);
            PullJobStatus pullStatus = k8sClient.pullJobStatus(jobName);
            LOG.info(String.format("pull job status(%s)", pullStatus));
            if (!pullStatus.equals(PullJobStatus.SUCCESS)){
                jobStatus = TenantJobStatus.PULL_FAIL;
            }

            TimeUnit.SECONDS.sleep(3);
            boolean deleteStatus = k8sClient.deleteJob(jobTemplate);
            LOG.info(String.format("delete job status(%s)", deleteStatus));
            if (!deleteStatus) {
                jobStatus = TenantJobStatus.DELETE_FAIL;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jobStatus;
    }*/

    private Job prepareJob(String tenantId, String jobName, String containerName, String containerImage) {
        Map<String, String> jobLabel = new HashMap<>();
        jobLabel.put("tenant","CreateTenant");

        return new JobBuilder()
                .withApiVersion("batch/v1")
                .withNewMetadata()
                .withName(jobName)
                .withLabels(jobLabel)
                .endMetadata()
                .withNewSpec()
                .withNewTemplate()
                .withNewSpec()
                .addNewContainer()
                .withName(containerName)
                .withImage(containerImage)
                .addNewEnv().withName(ENV_PLATFORM_TLS_SERVERTYPE).withValue(PLATFORM_TLS_SERVERTYPE_JOB)
                .withName(ENV_PLATFORM_TLS_TENANTID).withValue(tenantId)
                .endEnv()
                .endContainer()
                .withRestartPolicy("Never")
                .endSpec()
                .endTemplate()
                .endSpec()
                .build();
    }
}
