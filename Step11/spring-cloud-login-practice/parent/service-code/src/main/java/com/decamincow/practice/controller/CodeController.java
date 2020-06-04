package com.decamincow.practice.controller;

import com.decamincow.practice.CodeService;
import com.decamincow.practice.model.AuthCode;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CodeController
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 9:55 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/code")
public class CodeController {

    @Reference
    private CodeService codeService;

    /**
     * @description ⽣成验证码并发送到对应邮箱，成功true，失败false
     *
     * @since 1.0
     * @Author decamincow
     * @Date 2020/5/27 9:58 AM
     */
    @GetMapping("/create/{email}")
    public boolean create(@PathVariable String email){
        return codeService.createCode(AuthCode.builder().email(email).build());
    }

    /**
     * @description 校验验证码是否正确，0正确1错误2超时
     *
     * @since 1.0
     * @Author decamincow
     * @Date 2020/5/27 9:58 AM
     */
    @GetMapping("/validate/{email}/{code}")
    public int validate(@PathVariable String email, @PathVariable String code){
        return codeService.validateCode(AuthCode.builder().email(email).code(code).build());
    }
}
