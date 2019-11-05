package com.sf.hop;

import com.sf.hop.user.job.JobTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        LOG.info("ApplicationRunner args:" + args.getOptionNames().toString());

        String serverType = getArgValue(args,"server.type");
        if (!"job".equals(serverType)) {
            return;
        }
        String tenantId = getArgValue(args,"tenantId");
        LOG.info("start of application run for:" + tenantId);
        JobTask jobTask = new JobTask(tenantId);
        jobTask.doTask();
        LOG.info("end of application run");

        int status = 1;
        if("BizxTest".equals(tenantId)) {
            status = 0;
        }
        System.exit(status);
    }

    private String getArgValue(ApplicationArguments args, String key) {
        List<String> values = args.getOptionValues(key);
        if (values==null || values.isEmpty()) {
            return null;
        } else {
            return values.get(0);
        }
    }
}