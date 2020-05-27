package com.decamincow.practice.service;

import com.decamincow.practice.model.UserInfo;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 2:15 AM
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 创建用户
     * @param email
     * @param password
     * @param code
     * @return
     */
    UserInfo createUser(String email, String password, String code);

    /**
     * 通过邮箱判断是否已经注册
     * @param email
     * @return
     */
    Boolean isRegistered(String email);

    /**
     * 通过邮箱和密码进行登录
     * @param email
     * @param password
     * @return
     */
    String login(String email, String password);

    /**
     * 通过 token 获取用户信息
     * @param token
     * @return
     */
    String getUserInfoByToken(String token);
}