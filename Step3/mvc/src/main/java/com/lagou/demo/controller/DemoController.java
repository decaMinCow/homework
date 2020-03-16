package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotations.LagouAutowired;
import com.lagou.edu.mvcframework.annotations.LagouController;
import com.lagou.edu.mvcframework.annotations.LagouRequestMapping;
import com.lagou.edu.mvcframework.annotations.LagouSecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@LagouController
@LagouSecurity(value={"zhangsan"})
@LagouRequestMapping("/demo")
public class DemoController {


    @LagouAutowired
    private IDemoService demoService;


    /**
     * URL: /demo/handle01?username=zhangsan
     * @param request
     * @param response
     * @param username
     * @return
     */
    @LagouRequestMapping("/handle01")
    @LagouSecurity(value={"lisi","wangwu"})
    public String handler(HttpServletRequest request, HttpServletResponse response,String username) throws IOException {
//        response.setContentType("text/html; charset=UTF-8");//注意text/html，和application/html
//        response.getWriter().print("<html><body><script type='text/javascript'>alert('无权下载文件！');</script></body></html>");
//        response.getWriter().close();
        return demoService.get(username);
    }
}
