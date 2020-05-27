package com.decamincow.learning.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaServerApp8762
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 5:34 PM
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp8762 {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp8762.class, args);
    }
}