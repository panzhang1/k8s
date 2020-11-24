package com.successfactors.platform.k8s.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobStatusCallbackMock implements JobStatusCallback {
    private static final Logger LOG = LoggerFactory.getLogger(JobStatusCallbackMock.class);
    @Override
    public void success(String jobName, int succeeded) {
        LOG.info(String.format("At least one job(%s) attempt succeeded(%s)",jobName,succeeded));
    }

    @Override
    public void failure(String jobName, int failed) {
        LOG.info(String.format("At least one job(%s) attempt failed(%s)",jobName,failed));
    }
}
