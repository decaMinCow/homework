package com.decamincow.learning.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.decamincow.learning.springcloud.ResumeService;
import com.decamincow.learning.springcloud.config.SentinelHandlersClass;
import com.decamincow.learning.springcloud.model.Resume;
import org.apache.dubbo.config.annotation.Reference;
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

    @Reference
    private ResumeService resumeService;

    // /autodeliver/checkState/1545132
    @GetMapping("/checkState/{userId}")
    @SentinelResource(value = "findResumeOpenState",blockHandlerClass = SentinelHandlersClass.class,
            blockHandler = "handleException",fallbackClass = SentinelHandlersClass.class,fallback = "handleError")
    public Resume findResumeOpenState(@PathVariable Long userId) {

        return resumeService.findDefaultResumeByUserId(userId);

    }
}