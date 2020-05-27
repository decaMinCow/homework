package com.decamincow.learning.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName com.decamincow.learning.springcloud.controller.AutodeliverApplcation8090
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 3:11 AM
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker // 开启熔断
public class AutodeliverApplcation8090 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplcation8090.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}