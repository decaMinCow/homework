package com.decamincow.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @ClassName ConfigServerApplication
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 7:23 PM
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer  // 开启配置服务器功能
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
