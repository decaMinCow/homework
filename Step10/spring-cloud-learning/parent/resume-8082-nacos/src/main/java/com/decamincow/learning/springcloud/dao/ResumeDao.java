package com.decamincow.learning.springcloud.dao;

import com.decamincow.learning.springcloud.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ResumeDao
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 2:13 AM
 * @Version 1.0
 **/
public interface ResumeDao extends JpaRepository<Resume, Long> {
}
