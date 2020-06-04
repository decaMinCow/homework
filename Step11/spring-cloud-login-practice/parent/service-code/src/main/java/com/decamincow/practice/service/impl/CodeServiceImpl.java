package com.decamincow.practice.service.impl;

import com.decamincow.practice.CodeService;
import com.decamincow.practice.EmailService;
import com.decamincow.practice.dao.CodeDao;
import com.decamincow.practice.model.AuthCode;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName CodeServiceImpl
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 12:58 PM
 * @Version 1.0
 **/
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDao codeDao;

    @Reference
    private EmailService emailService;

    private final static int EXPIRE_TIME_AFTER_MIN = 2;
    private final static int VALIDATE_CODE_SUCCESS = 0;
    private final static int VALIDATE_CODE_FAIL = 1;
    private final static int VALIDATE_CODE_EXPIRED = 10;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public boolean createCode(AuthCode authCode) {
        /**
         * 生成过期时间
         */
        Date expireTime = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(expireTime);
        c.add(Calendar.MINUTE, EXPIRE_TIME_AFTER_MIN);
        expireTime = c.getTime();

        /**
         * 生成随机6位数
         */
        String code = String.valueOf((int)((Math.random()*9+1)*100000));

        authCode.setExpireTime(expireTime);
        authCode.setCode(code);

        try {
            codeDao.save(authCode);
            emailService.sendEmail(authCode.getEmail(), authCode.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

        return true;
    }

    @Override
    public Integer validateCode(AuthCode authCode) {

        Example<AuthCode> example = Example.of(authCode);
        AuthCode authCodeResult;
        /**
         * 验证码不对返回1
         */
        try {
            authCodeResult = codeDao.findOne(example).get();
        } catch (Exception e) {
            return VALIDATE_CODE_FAIL;
        }

        /**
         * 超时返回2
         */
        if(new Date().after(authCodeResult.getExpireTime())){
            return VALIDATE_CODE_EXPIRED;
        }

        /**
         * 成功返回0
         */
        return VALIDATE_CODE_SUCCESS;
    }
}
