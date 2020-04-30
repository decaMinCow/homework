package com.decamincow.registry.controller;

import com.decamincow.registry.dao.ResumeDao;
import com.decamincow.registry.model.po.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName MyController
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/30 2:45 PM
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private ResumeDao resumeDao;

    @GetMapping(value = "/getOK")
    public String getOK(){
        return "OK";
    }

    @GetMapping(value = "/user")
    public List<Resume> getUser() {
        return resumeDao.findAll();
    }
}
