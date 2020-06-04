package com.decamincow.practice;

/**
 * @ClassName EmailService
 * @Description TODO
 * @Author decamincow
 * @Date 2020/6/4 8:03 PM
 * @Version 1.0
 **/
public interface EmailService {

    boolean sendEmail(String receiver, String text);
}