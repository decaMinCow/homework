package com.decamincow.learning.springcloud.controller;

import com.decamincow.learning.springcloud.controller.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AutodeliverController
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 3:10 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    private ResumeServiceFeignClient resumeServiceFeignClient;

    // /autodeliver/checkState/1545132
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {

        return resumeServiceFeignClient.findDefaultResumeState(userId);

    }
}