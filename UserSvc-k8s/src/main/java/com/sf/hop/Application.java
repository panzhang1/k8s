package com.sf.hop;

import com.sf.hop.tls.TlsProperties;
import com.sf.hop.tls.TlsIndividualTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;


@SpringBootApplication
@EnableConfigurationProperties(TlsProperties.class)
public class Application implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TlsProperties jobProperties;

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

        LOG.info(env.getProperty("platform.tls.servertype") + "," + env.getProperty("platform.tls.tenantid"));

        String serverType = jobProperties.getServertype();
        String tenantId = jobProperties.getTenantid();

        LOG.info(String.format("start of application run for %s server, tenantId is %s",serverType, tenantId));

        if (!"job".equals(serverType)) {
            return;
        }
        TlsIndividualTask jobTask = new TlsIndividualTask(tenantId);
        jobTask.doTask();
        LOG.info("end of application run");

        int status = 1;
        if("BizxTest".equals(tenantId)) {
            status = 0;
        }
        System.exit(status);
    }
}