package com.sf.hop.tls;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="platform.tls")
public class TlsProperties {
    private String tenantid;
    private String servertype;

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public String getServertype() {
        return servertype;
    }

    public void setServertype(String servertype) {
        this.servertype = servertype;
    }
}
