package com.decamincow.practice.service;

import com.decamincow.practice.model.AuthCode;

/**
 * @ClassName CodeService
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 12:33 PM
 * @Version 1.0
 **/
public interface CodeService {

    /**
     * 生成验证码
     * @param authCode
     * @return
     */
    boolean createCode(AuthCode authCode);

    /**
     * 校验验证码是否正确，0正确1错误2超时
     * @param authCode
     * @return
     */
    Integer validateCode(AuthCode authCode);
}
