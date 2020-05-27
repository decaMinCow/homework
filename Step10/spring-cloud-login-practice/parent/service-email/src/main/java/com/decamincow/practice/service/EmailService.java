package com.decamincow.practice.service;

/**
 * @ClassName EmailService
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 3:19 PM
 * @Version 1.0
 **/
public interface EmailService {

    boolean sendEmail(String receiver, String text);
}
