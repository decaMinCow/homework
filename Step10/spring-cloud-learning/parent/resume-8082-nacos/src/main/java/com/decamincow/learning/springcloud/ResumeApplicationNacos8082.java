package com.decamincow.learning.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ResumeApplication
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 2:23 AM
 * @Version 1.0
 **/
@SpringBootApplication
@EntityScan("com.decamincow.learning.springcloud.model")
@EnableDiscoveryClient // 开启注册中心客户端
public class ResumeApplicationNacos8082 {

    public static void main(String[] args) {
        SpringApplication.run(ResumeApplicationNacos8082.class, args);
    }

}
