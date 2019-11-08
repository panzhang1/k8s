package com.sf.hop.tls.controller;

import com.sf.hop.tls.model.ProvisionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TLSController {
    private static final Logger LOG = LoggerFactory.getLogger(TLSController.class);
    
    @GetMapping(value = "/createTenant/{tenantId}")
    public ProvisionStatus createTenant(@PathVariable(value="tenantId") String tenantId) {
        ProvisionStatus status = new ProvisionStatus();
        status.setStatus("200");

        return status;
    }
}