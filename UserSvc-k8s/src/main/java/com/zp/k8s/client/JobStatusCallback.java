package com.zp.k8s.client;

public interface JobStatusCallback {
    void success(String jobName, int succeeded);

    void failure(String jobName, int failed);
}
