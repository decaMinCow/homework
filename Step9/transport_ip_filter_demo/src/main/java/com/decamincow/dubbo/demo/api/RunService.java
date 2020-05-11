package com.decamincow.dubbo.demo.api;

public interface RunService {

    String run(String name);

    default String goRun(String name) {
        return "Goodbye, " + name;
    }
}
