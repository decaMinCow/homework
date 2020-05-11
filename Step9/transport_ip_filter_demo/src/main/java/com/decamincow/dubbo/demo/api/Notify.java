package com.decamincow.dubbo.demo.api;

import java.util.List;
import java.util.Map;

public interface Notify {
    void oninvoke(String request);

    void onreturn(String response, String request);

    void onthrow(Throwable ex, String request);

    List<String> getInvokes();

    Map<String, String> getReturns();

    Map<String, Throwable> getExceptions();
}
