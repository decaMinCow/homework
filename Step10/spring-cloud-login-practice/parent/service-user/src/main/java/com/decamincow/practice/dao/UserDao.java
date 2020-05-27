package com.decamincow.practice.dao;

import com.decamincow.practice.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 2:13 AM
 * @Version 1.0
 **/
public interface UserDao extends JpaRepository<UserInfo, Long> {
}
