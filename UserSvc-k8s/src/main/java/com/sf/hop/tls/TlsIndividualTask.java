package com.sf.hop.tls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TlsIndividualTask {
    private static final Logger LOG = LoggerFactory.getLogger(TlsIndividualTask.class);
    private String tenantId;
    public TlsIndividualTask(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean doTask() {
        LOG.info(String.format("start doTask for %s",tenantId));

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOG.info("end doTask");
        return true;
    }
}
