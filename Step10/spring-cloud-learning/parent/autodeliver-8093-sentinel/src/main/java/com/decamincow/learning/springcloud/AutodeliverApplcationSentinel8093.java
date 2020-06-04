package com.decamincow.learning.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName com.decamincow.learning.springcloud.controller.AutodeliverApplcation8091
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 3:11 AM
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AutodeliverApplcationSentinel8093 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplcationSentinel8093.class, args);
    }

}