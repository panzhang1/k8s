package com.sf.hop.tls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TlsIndividualTask {
    private static final Logger LOG = LoggerFactory.getLogger(TlsIndividualTask.class);
    private String tenantId;
    public TlsIndividualTask(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean doTask() {
        LOG.info(String.format("start doTask for %s",tenantId));

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOG.info("end doTask");
        return true;
    }
}
