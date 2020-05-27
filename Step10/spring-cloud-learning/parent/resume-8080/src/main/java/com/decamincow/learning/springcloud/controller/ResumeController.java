package com.decamincow.learning.springcloud.controller;

import com.decamincow.learning.springcloud.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ResumeController
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 2:21 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Value("${server.port}")
    private Integer port;

    // /resume/openstate/1545132
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId) {
        // 模拟请求超时,触发服务消费者端熔断降级
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("服务提供者端口号：" + port);
        return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
    }
}