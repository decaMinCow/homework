package com.decamincow.learning.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName com.decamincow.learning.springcloud.controller.AutodeliverApplcation8091
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 3:11 AM
 * @Version 1.0
 **/
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class AutodeliverApplcationNacosSentinelDubbo8094 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplcationNacosSentinelDubbo8094.class, args);
    }

}