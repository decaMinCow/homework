package com.decamincow.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName EmailApplication
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 3:19 PM
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class EmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }
}
