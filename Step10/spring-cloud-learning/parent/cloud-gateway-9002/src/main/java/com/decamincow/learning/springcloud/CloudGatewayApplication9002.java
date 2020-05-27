package com.decamincow.learning.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName CloudGatewayApplication9002
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/26 11:58 AM
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewayApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication9002.class, args);
    }
}
