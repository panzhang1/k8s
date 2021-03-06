package com.sf.hop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * It can only scan the component under this package
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class Application {
    
    //Client LoadBalance(Ribbon) supported
//    @LoadBalanced
//    @Bean
//    public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}