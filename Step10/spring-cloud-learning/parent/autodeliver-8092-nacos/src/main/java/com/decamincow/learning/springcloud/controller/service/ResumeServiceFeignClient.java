package com.decamincow.learning.springcloud.controller.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// 配置注册中心里的 provider 名称
@FeignClient(value = "resume")
@RequestMapping("/resume")
@Service
public interface ResumeServiceFeignClient {

    // 和 provider 中接口一致便可
    @GetMapping("/openstate/{userId}")
    Integer findDefaultResumeState(@PathVariable("userId") Long userId);

}