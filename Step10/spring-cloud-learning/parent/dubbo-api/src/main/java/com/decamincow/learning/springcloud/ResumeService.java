package com.decamincow.learning.springcloud;

import com.decamincow.learning.springcloud.model.Resume;

/**
 * @ClassName ResumeService
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/25 2:15 AM
 * @Version 1.0
 **/
public interface ResumeService {

    Resume findDefaultResumeByUserId(Long userId);
}
