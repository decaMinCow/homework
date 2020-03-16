package com.decamincow.sss.dao;

import com.decamincow.sss.model.po.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ResumeDao extends JpaRepository<Resume,Long>, JpaSpecificationExecutor<Resume> {


}
