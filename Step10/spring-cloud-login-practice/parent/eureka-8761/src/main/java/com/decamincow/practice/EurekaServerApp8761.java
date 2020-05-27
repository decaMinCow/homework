package com.decamincow.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaServerApp8761
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 5:34 PM
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp8761 {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp8761.class, args);
    }
}