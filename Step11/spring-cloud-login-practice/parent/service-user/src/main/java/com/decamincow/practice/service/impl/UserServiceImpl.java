package com.decamincow.practice.service.impl;

import com.decamincow.practice.CodeService;
import com.decamincow.practice.dao.UserDao;
import com.decamincow.practice.model.AuthCode;
import com.decamincow.practice.model.UserInfo;
import com.decamincow.practice.service.UserService;
import com.decamincow.practice.util.TokenGen;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 2:17 AM
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Reference
    private CodeService codeService;

    @Override
    public UserInfo createUser(String email, String password, String code) {
        /**
         * TODO 非注册成功先统一返回 null
         */
        if (codeService.validateCode(AuthCode.builder().email(email).code(code).build()) != 0) {
            return null;
        }

        UserInfo userInfo = UserInfo.builder().email(email).password(password).build();

        if (userDao.exists(Example.of(userInfo))) {
            return null;
        }

        UserInfo result = userDao.save(UserInfo.builder().email(email).password(password).build());

        /**
         * cookie 种入 token
         */
        Cookie cookie = new Cookie("token", TokenGen.genSign(email + password));
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        servletRequestAttributes.getResponse().addCookie(cookie);
        return result;
    }

    @Override
    public Boolean isRegistered(String email) {
        Example<UserInfo> example = Example.of(UserInfo.builder().email(email).build());
        if (userDao.count(example) > 0) {
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public String login(String email, String password) {
        Example<UserInfo> example;
        String result;
        try {
            example = Example.of(UserInfo.builder().email(email).password(password).build());
            UserInfo userInfo = userDao.findOne(example).get();
            result = userInfo.getEmail();

            /**
             * 一般这里是写到另一个 token 表中 并且是异步推送过去的，这里暂时先把 token 记录到一个表中
             */
            String token = TokenGen.genSign(email + password);
            userInfo.setToken(token);
            userDao.save(userInfo);

            /**
             * cookie 种入 token
             */
            Cookie cookie = new Cookie("token", TokenGen.genSign(email + password));
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            servletRequestAttributes.getResponse().addCookie(cookie);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "";
        }

        return result;
    }

    @Override
    public String getUserInfoByToken(String token) {
        Example<UserInfo> example;
        String result;
        try {
            example = Example.of(UserInfo.builder().token(token).build());
            result = userDao.findOne(example).get().getEmail();
        } catch (Exception e) {
            return "";
        }

        return result;
    }
}