package com.decamincow.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName CodeApplication
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 1:38 PM
 * @Version 1.0
 **/
@SpringBootApplication
@EntityScan("com.decamincow.practice.model")
@EnableDiscoveryClient // 开启注册中心客户端
@EnableFeignClients
public class CodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeApplication.class, args);
    }
}
