package com.decamincow.sss.controller;

import com.decamincow.sss.dao.ResumeDao;
import com.decamincow.sss.model.AdminUser;
import com.decamincow.sss.model.po.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/getSession")
    public void getSession(HttpServletRequest request){
        request.getSession().setAttribute("message",request.getQueryString());
    }
    @GetMapping("/setSession")
    public Map<String,Object> setSession(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("sessionId",request.getSession().getId());
        map.put("message",request.getSession().getAttribute("message"));
        return map;
    }

    @PostMapping(value = "/user")
    public void createUser(String name,
                           String phone,
                           String address) {
        resumeDao.save(Resume.builder().address(address).name(name).phone(phone).build());
    }


    @DeleteMapping(value = "/user")
    public void removeUser() {
        resumeDao.delete(Resume.builder().id(4L).build());
    }

    @PutMapping(value = "/user")
    public void updateUser(String id,
                           String name,
                           String phone,
                           String address) {
        resumeDao.save(Resume.builder().id(Long.parseLong(id)).address(address).name(name).phone(phone).build());
    }

    @GetMapping(value = "/user")
    public List<Resume> getUser() {
        return resumeDao.findAll();
    }

    @PostMapping(value = "/gologin")
    public void gologin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        System.out.println("session_id: " + session.getId());
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