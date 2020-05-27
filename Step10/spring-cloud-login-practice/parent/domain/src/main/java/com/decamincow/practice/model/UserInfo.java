package com.decamincow.practice.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;


/**
 * @ClassName UserInfo
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 9:46 AM
 * @Version 1.0
 **/
@Data
@Builder
@Entity
@Table(name="t_userinfo")
public class UserInfo {

    @Tolerate
    public UserInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键
    private String email; // 邮箱
    private String password; // 密码
//    private String code; // 验证码
    private String token; // 凭据
}