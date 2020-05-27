package com.decamincow.practice.controller.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "email")
@RequestMapping("/email")
@Service
public interface EmailServiceFeignClient {

    @GetMapping("/{email}/{code}")
    boolean sendEmail(@PathVariable(value="email") String email, @PathVariable(value="code") String code);
}