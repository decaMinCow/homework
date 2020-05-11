package com.decamincow.dubbo.demo.impl;


import com.decamincow.dubbo.demo.AnnotationConstants;
import com.decamincow.dubbo.demo.api.HelloService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

@Service(version = AnnotationConstants.VERSION, methods = {@Method(name = "sayGoodbye", timeout = 250, retries = 0)})
public class AnnotationHelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
//        System.out.println("ip: " + clientIP);
//        System.out.println("provider received invoke of sayHello: " + name);
        sleepWhile();
        return "Annotation, hello " + name;
    }

    public String sayGoodbye(String name) {
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
        System.out.println("ip: " + clientIP);
        System.out.println("provider received invoke of sayGoodbye: " + name);
        sleepWhile();
        return "Goodbye, " + name;
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
