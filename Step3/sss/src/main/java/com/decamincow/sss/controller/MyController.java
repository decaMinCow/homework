package com.decamincow.sss.controller;

import com.decamincow.sss.dao.ResumeDao;
import com.decamincow.sss.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName MyController
 * @Description TODO
 * @Author decamincow
 * @Date 16/03/2020 4:44 PM
 * @Version 1.0
 **/
@Controller
@RequestMapping("/user")
public class MyController {

    @Autowired
    private ResumeDao resumeDao;

    @RequestMapping(value = { "/", "/index" })
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/gologin")
    public String gologin(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        AdminUser admin = (AdminUser)session.getAttribute("adminuser");
        model.addAttribute("adminuser",admin);
        model.addAttribute("alist", resumeDao.findAll());
        return "index";
    }

}