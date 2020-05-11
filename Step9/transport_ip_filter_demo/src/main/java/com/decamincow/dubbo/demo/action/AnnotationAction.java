package com.decamincow.dubbo.demo.action;

import com.decamincow.dubbo.demo.AnnotationConstants;
import com.decamincow.dubbo.demo.api.GreetingService;
import com.decamincow.dubbo.demo.api.HelloService;
import com.decamincow.dubbo.demo.api.RunService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class AnnotationAction {

    @Reference(interfaceClass = HelloService.class,
            version = AnnotationConstants.VERSION,
            timeout = 30000,
            methods = {@Method(name = "sayHello", timeout = 30000, retries = 5)})
    private HelloService helloService;

    @Reference(interfaceClass = GreetingService.class,
            version = AnnotationConstants.VERSION,
            timeout = 30000,
            methods = {@Method(name = "greeting", timeout = 30000, retries = 5)})
    private GreetingService greetingService;

    @Reference(interfaceClass = RunService.class,
            version = AnnotationConstants.VERSION,
            timeout = 30000,
            methods = {@Method(name = "run", timeout = 30000, retries = 5)})
    private RunService runService;

    public String doSayHello(String name) {
        try {
            return helloService.sayHello(name);
        } catch (Exception e) {
            e.printStackTrace();
            return "Throw Exception";
        }
    }

    public String doSayGoodbye(String name) {
        try {
            return helloService.sayGoodbye(name);
        } catch (Exception e) {
            e.printStackTrace();
            return "Throw Exception";
        }

    }

    public String doGreeting(String name) {
        try {
            return greetingService.greeting(name);
        } catch (Exception e) {
            e.printStackTrace();
            return "Throw Exception";
        }

    }

    public String replyGreeting(String name) {
        try {
            return greetingService.replyGreeting(name);
        } catch (Exception e) {
            e.printStackTrace();
            return "Throw Exception";
        }
    }

    public String doRun(String name) {
        try {
            return runService.run(name);
        } catch (Exception e) {
            e.printStackTrace();
            return "Throw Exception";
        }
    }
}
