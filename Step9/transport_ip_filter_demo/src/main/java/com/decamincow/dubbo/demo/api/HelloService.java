package com.decamincow.dubbo.demo.api;

public interface HelloService {

    String sayHello(String name);

    default String sayGoodbye(String name) {
        return "Goodbye, " + name;
    }
}
