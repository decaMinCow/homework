package com.decamincow.practice.controller;

import com.decamincow.practice.model.UserInfo;
import com.decamincow.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 9:36 AM
 * @Version 1.0
 **/
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @true/false 注册接⼝，true成功，false失败
     *
     * @since 1.0
     * @Author decamincow
     * @Date 2020/5/27 9:48 AM
     */
    @GetMapping("/register/{email}/{password}/{code}")
    public boolean register(@PathVariable String email, @PathVariable String password, @PathVariable String code){
        UserInfo userInfo = userService.createUser(email, password, code);
        if (userInfo != null){
            return true;
        }
        return false;
    }

    /**
     * @是否已注册，根据邮箱判断,true代表已经注册过，false代表尚未注册
     *
     * @since 1.0
     * @Author decamincow
     * @Date 2020/5/27 9:49 AM
     */
    @GetMapping("/isRegistered/{email}")
    public boolean isRegistered(@PathVariable String email){
        return userService.isRegistered(email);
    }

    /**
     * @登录接⼝，验证⽤户名密码合法性，根据⽤户名和
     * 密码⽣成token，token存⼊数据库，并写⼊cookie中，登录成功返回邮箱地址，重定向到欢迎⻚
     *
     * @since 1.0
     * @Author decamincow
     * @Date 2020/5/27 9:50 AM
     */
    @GetMapping("/login/{email}/{password}")
    public String login(@PathVariable String email, @PathVariable String password){
        return userService.login(email, password);
    }

    /**
     * @description 根据token查询⽤户登录邮箱接⼝
     *
     * @since 1.0
     * @Author decamincow
     * @Date 2020/5/27 9:53 AM
     */
    @GetMapping("/info/{token}")
    public String getToken(@PathVariable String token){
        return userService.getUserInfoByToken(token);
    }

}
