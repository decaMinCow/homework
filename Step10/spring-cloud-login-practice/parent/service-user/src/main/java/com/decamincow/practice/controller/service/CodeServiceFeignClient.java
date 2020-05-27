package com.decamincow.practice.controller.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName CodeServiceFeignClient
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 5:38 PM
 * @Version 1.0
 **/
@FeignClient(value = "code")
@RequestMapping("/code")
@Service
public interface CodeServiceFeignClient {
    @GetMapping("/validate/{email}/{code}")
    int validate(@PathVariable("email") String email, @PathVariable("code") String code);
}