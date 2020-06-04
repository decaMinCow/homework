package com.decamincow.practice.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @ClassName EmailManager
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 2:51 PM
 * @Version 1.0
 **/
@Component
public class EmailManager {

    @Autowired
    JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String emailUsername;

    private static final String TITLE = "注册验证码";

    public boolean sendEmail(String receiver, String text) {

        try {
            SimpleMailMessage mainMessage = new SimpleMailMessage();
            mainMessage.setFrom(emailUsername);
            mainMessage.setTo(receiver);
            mainMessage.setSubject(TITLE);
            mainMessage.setText(text);
            jms.send(mainMessage);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
