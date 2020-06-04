package com.decamincow.practice.service.impl;

import com.decamincow.practice.EmailService;
import com.decamincow.practice.manager.EmailManager;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName EmailServiceImpl
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 3:20 PM
 * @Version 1.0
 **/
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailManager emailManager;

    @Override
    public boolean sendEmail(String receiver, String text) {
        return emailManager.sendEmail(receiver, text);
    }
}
