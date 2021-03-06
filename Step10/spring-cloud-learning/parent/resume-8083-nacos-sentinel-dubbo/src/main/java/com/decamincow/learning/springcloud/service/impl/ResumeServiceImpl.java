package com.decamincow.learning.springcloud.service.impl;

import com.decamincow.learning.springcloud.ResumeService;
import com.decamincow.learning.springcloud.dao.ResumeDao;
import com.decamincow.learning.springcloud.model.Resume;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

/**
 * @ClassName ResumeServiceImpl
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 2:17 AM
 * @Version 1.0
 **/
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Override
    public Resume findDefaultResumeByUserId(Long userId) {
        Example<Resume> example = Example.of(Resume.builder().userId(userId).isDefault(1).build());
        return resumeDao.findOne(example).get();
    }
}
