package com.decamincow.dubbo.demo.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "com.decamincow.dubbo.demo.action")
@PropertySource("classpath:/spring/dubbo-consumer.properties")
@ComponentScan(value = {"com.decamincow.dubbo.demo.action"})
public class ConsumerConfiguration {

}
