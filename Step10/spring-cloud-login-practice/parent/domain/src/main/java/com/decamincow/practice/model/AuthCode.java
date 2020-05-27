package com.decamincow.practice.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName AuthCode
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 12:34 PM
 * @Version 1.0
 **/
@Data
@Builder
@Entity
@Table(name="auth_code")
public class AuthCode {
    @Tolerate
    public AuthCode() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键
    private String email; // 邮箱
    private String code; // 验证码
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime; // 创建时间
    private Date expireTime; // 过期时间
}