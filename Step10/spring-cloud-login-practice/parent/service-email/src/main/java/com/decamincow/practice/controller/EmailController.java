package com.decamincow.practice.controller;

import com.decamincow.practice.manager.EmailManager;
import com.decamincow.practice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EmailController
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 9:59 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("/{email}/{code}")
    public boolean sendEmail(@PathVariable String email, @PathVariable String code){
        return emailService.sendEmail(email, code);
    }
}
