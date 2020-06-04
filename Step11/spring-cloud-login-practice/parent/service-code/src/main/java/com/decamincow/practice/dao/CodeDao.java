package com.decamincow.practice.dao;

import com.decamincow.practice.model.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName CodeDao
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 12:33 PM
 * @Version 1.0
 **/
public interface CodeDao extends JpaRepository<AuthCode, Long> {
}
