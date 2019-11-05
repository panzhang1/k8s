package com.sf.hop.user.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobTask {
    private static final Logger LOG = LoggerFactory.getLogger(JobTask.class);
    private String tenantId;
    public JobTask(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean doTask() {
        LOG.info(String.format("start doTask for %s",tenantId));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOG.info("end doTask");
        return true;
    }
}
