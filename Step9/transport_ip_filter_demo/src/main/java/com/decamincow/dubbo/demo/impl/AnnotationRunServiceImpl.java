package com.decamincow.dubbo.demo.impl;


import com.decamincow.dubbo.demo.AnnotationConstants;
import com.decamincow.dubbo.demo.api.RunService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

@Service(version = AnnotationConstants.VERSION, methods = {@Method(name = "goRun", timeout = 250, retries = 0)})
public class AnnotationRunServiceImpl implements RunService {

    @Override
    public String run(String name) {
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
//        System.out.println("ip: " + clientIP);
//        System.out.println("provider received invoke of run: " + name);
        sleepWhile();
        return "Annotation, run " + name;
    }

    public String goRun(String name) {
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
        System.out.println("ip: " + clientIP);
        System.out.println("provider received invoke of goRun: " + name);
        sleepWhile();
        return "goRun, " + name;
    }

    /**
     * 随机 0~100 毫秒
     */
    private void sleepWhile() {
        try {
            Thread.sleep(new Double(Math.random()*101).longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
