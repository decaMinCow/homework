package com.decamincow.practice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该类用于模拟，我们要使用共享的那些配置信息做一些事情
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${mysql.url}")
    private String mysqlUrl;


    @GetMapping("/viewconfig")
    public String viewconfig() {
        return "mysqlUrl==>" + mysqlUrl;
    }
}
