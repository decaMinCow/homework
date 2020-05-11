package com.decamincow.dubbo.demo.impl;

import com.decamincow.dubbo.demo.AnnotationConstants;
import com.decamincow.dubbo.demo.api.GreetingService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

@Service(version = AnnotationConstants.VERSION)
public class AnnotationGreetingServiceImpl implements GreetingService {

    @Override
    public String greeting(String name) {
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
//        System.out.println("ip: " + clientIP);
//        System.out.println("provider received invoke of greeting: " + name);
        sleepWhile();
        return "Annotation, greeting " + name;
    }

    public String replyGreeting(String name) {
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
        System.out.println("ip: " + clientIP);
        System.out.println("provider received invoke of replyGreeting: " + name);
        sleepWhile();
        return "Annotation, fine " + name;
    }

    /**
     * 随机 100 毫秒
     */
    private void sleepWhile() {
        try {
            Thread.sleep(new Double(Math.random()*101).longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
