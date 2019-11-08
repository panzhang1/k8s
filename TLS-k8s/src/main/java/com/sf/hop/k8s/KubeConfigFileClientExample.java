package com.sf.hop.k8s;

import com.google.gson.reflect.TypeToken;
import com.sf.hop.k8s.config.K8sConfig;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.BatchV1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import io.kubernetes.client.util.Watch;
import io.kubernetes.client.util.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A simple example of how to use the Java API from an application outside a kubernetes cluster
 *
 * <p>Easiest way to run this: mvn exec:java
 * -Dexec.mainClass="io.kubernetes.client.examples.KubeConfigFileClientExample"
 *
 * <p>From inside $REPO_DIR/examples
 */
public class KubeConfigFileClientExample {
    private static final Logger LOG = LoggerFactory.getLogger(KubeConfigFileClientExample.class);

    public static void main(String[] args) {
        KubeConfigFileClientExample example = new KubeConfigFileClientExample();
        try {
            example.testK8s();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testK8s() throws IOException, ApiException {
        // file path to your KubeConfig
        String kubeConfigPath = K8sConfig.kubeConfigPath;
        //String kubeConfigPath = "~/.kube/config";
        // loading the out-of-cluster config, a kubeconfig from file-system
        ApiClient client =
                ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
        // set the global default api-client to the in-cluster one from above
        Configuration.setDefaultApiClient(client);

        //getPods();
        //deleteJob();
        //startJob();
        watchJob(client);
    }

    private void getPods() throws ApiException {
        // the CoreV1Api loads default api-client from global configuration.
        CoreV1Api api = new CoreV1Api();
        // invokes the CoreV1Api client
        V1PodList list = api.listNamespacedPod(K8sConfig.namespace,null,null,null,null,null,null,null,null);
        //V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            LOG.info(item.getMetadata().getName());
        }
    }

    private void startJob() throws IOException, ApiException {
        File file = new File(K8sConfig.job_createtenant_activity_yaml);
        V1Job yamlDeployment = (V1Job) Yaml.load(file);

        BatchV1Api api = new BatchV1Api();
        V1Job viJob = api.createNamespacedJob(K8sConfig.namespace, yamlDeployment,null, null, null);
        LOG.info(viJob.toString());
    }

    private void deleteDeployment() throws IOException, ApiException{}

    private void watchJob(ApiClient client) throws IOException, ApiException{
        File file = new File(K8sConfig.job_createtenant_activity_yaml);
        V1Job yamlDeployment = (V1Job) Yaml.load(file);
        BatchV1Api api = new BatchV1Api();
        Watch<V1Namespace> watch =
                Watch.createWatch(
                        client,
                        api.createNamespacedJobCall(K8sConfig.namespace, yamlDeployment,null, null, null,null,null),
                        new TypeToken<Watch.Response<V1Namespace>>() {}.getType());

        try {
            for (Watch.Response<V1Namespace> item : watch) {
                System.out.printf("%s : %s%n", item.type, item.object.getMetadata().getName());
            }
        } finally {
            watch.close();
        }
    }

    //TODO: delete should delete dependency(pods), currently it does not delete it
    //ttlSecondsAfterFinished only supported by Kubernetes v1.12 Alpha
    private void deleteJob() throws IOException, ApiException {
        BatchV1Api api = new BatchV1Api();
        //k8s client bug: https://github.com/kubernetes/kubernetes/issues/65121
        //delete will throw IllegalStateException due to wrong Json object match
        try {
            V1Status viStatus = api.deleteNamespacedJob(K8sConfig.job_name, K8sConfig.namespace, "false", null, null, null, null, null);
            System.out.println(viStatus);
        } catch (Exception e) {
            if (e.getCause() instanceof IllegalStateException) {
                IllegalStateException ise = (IllegalStateException) e.getCause();
                if (ise.getMessage() != null && ise.getMessage().contains("Expected a string but was BEGIN_OBJECT")) {
                    LOG.info("Catching exception because of issue https://github.com/kubernetes/kubernetes/issues/65121");
                } else {
                    LOG.error("deleteNamespacedJob error",e);
                }
            } else {
                LOG.error("deleteNamespacedJob error",e);
            }
        //https://github.com/kubernetes/kubernetes/issues/43329
        //client bug2 when set value for propagationPolicy
        }
    }
}