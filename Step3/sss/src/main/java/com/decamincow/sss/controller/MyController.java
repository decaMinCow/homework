package com.decamincow.sss.controller;

import com.decamincow.sss.dao.ResumeDao;
import com.decamincow.sss.model.AdminUser;
import com.decamincow.sss.model.po.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    public String index(Model model, HttpServletRequest request) {
        List<Resume> list = resumeDao.findAll();
        HttpSession session = request.getSession();
        AdminUser admin = (AdminUser)session.getAttribute("adminuser");
        model.addAttribute("adminuser",admin);
        model.addAttribute("alist", list);
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/goadd")
    public String goadd() {
        return "addUser";
    }

    @RequestMapping("/gologin")
    public String gologin(Model model, HttpServletRequest request) {
        System.out.println("port: " + request.getServerPort());
        List<Resume> list = resumeDao.findAll();
        HttpSession session = request.getSession();
        AdminUser admin = (AdminUser)session.getAttribute("adminuser");
        model.addAttribute("adminuser",admin);
        model.addAttribute("alist", list);
        return "index";
    }

    @RequestMapping("/goupdate/{id}")
    public String goupdate(@PathVariable("id") int id, Model model){
        String idResult = id + "";
        Optional<Resume> resume = resumeDao.findById(Long.parseLong(idResult));
        Resume result = resume.get();
        model.addAttribute("user", result);
        return "updateUser";
    }

    @RequestMapping("/godelete/{id}")
    public void godelete(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        String idResult = id + "";
        resumeDao.delete(Resume.builder().id(Long.parseLong(idResult)).build());
        response.sendRedirect("/user/index");
    }

}