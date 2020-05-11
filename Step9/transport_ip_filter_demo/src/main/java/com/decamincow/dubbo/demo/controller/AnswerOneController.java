package com.decamincow.dubbo.demo.controller;

import com.decamincow.dubbo.demo.action.AnnotationAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MyController
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/11 2:40 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class AnswerOneController {

    @Autowired
    private AnnotationAction annotationAction;

    @GetMapping(value = "/hello")
    public String getOK(){
        // 模拟调用两个项目的 dubbo 服务
        return annotationAction.doSayGoodbye("decamincow") + "----" + annotationAction.replyGreeting("decamincow");
    }

}
