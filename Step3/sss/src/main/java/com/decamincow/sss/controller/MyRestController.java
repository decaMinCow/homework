package com.decamincow.sss.controller;

import com.decamincow.sss.dao.ResumeDao;
import com.decamincow.sss.model.AdminUser;
import com.decamincow.sss.model.po.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName MyController
 * @Description TODO
 * @Author decamincow
 * @Date 16/03/2020 3:49 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private ResumeDao resumeDao;

    @PostMapping(value = "/user")
    public void createUser() {
        resumeDao.save(Resume.builder().address("address").name("name").phone("phone").build());
    }


    @DeleteMapping(value = "/user")
    public void removeUser() {
        resumeDao.delete(Resume.builder().id(4L).build());
    }

    @PutMapping(value = "/user")
    public void updateUser() {
        resumeDao.save(Resume.builder().id(5L).address("address").name("name").phone("phone").build());
    }

    @GetMapping(value = "/user")
    public List<Resume> getUser() {
        return resumeDao.findAll();
    }

    @PostMapping(value = "/gologin")
    public void gologin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        AdminUser admin = AdminUser.builder().userName(userName).password(password).build();
        if("admin".equals(userName) && "admin".equals(password)){
            session.setAttribute("adminuser", admin);
            response.sendRedirect("/user/gologin");
        }else{
            response.sendRedirect("/user/gologin");
        }


    }

}