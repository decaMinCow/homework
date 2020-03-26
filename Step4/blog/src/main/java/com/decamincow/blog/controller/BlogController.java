package com.decamincow.blog.controller;

import com.decamincow.blog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName BlogController
 * @Description TODO
 * @Author decamincow
 * @Date 2020/3/26 2:22 PM
 * @Version 1.0
 **/
@Controller
public class BlogController {

    @Autowired
    IArticleService articleService;

    @RequestMapping(value = { "/", "/index" })
    public String index(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {
        model.addAttribute("articles", articleService.getAllArticles(pageNum, pageSize));
        return "client/index";
    }
}
