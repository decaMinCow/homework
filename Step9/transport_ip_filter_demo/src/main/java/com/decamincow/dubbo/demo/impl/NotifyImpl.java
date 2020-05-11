package com.decamincow.dubbo.demo.impl;

import com.decamincow.dubbo.demo.api.Notify;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("notify")
public class NotifyImpl implements Notify {
    private List<String> invokes = new ArrayList<>();
    private Map<String, String> returns = new HashMap<>();
    private Map<String, Throwable> exceptions = new HashMap<>();

    @Override
    public void oninvoke(String request) {
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
        System.out.println("ip: " + clientIP);
        System.out.println("oninvoke - request: " + request);
        invokes.add(request);
    }

    @Override
    public void onreturn(String response, String request) {
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
        System.out.println("ip: " + clientIP);
        System.out.println("onreturn - req: " + request + ", res: " + response);
        returns.put(request, response);
    }

    @Override
    public void onthrow(Throwable ex, String request) {
        // 获取调用方IP地址
        String clientIP = RpcContext.getContext().getRemoteHost();
        System.out.println("ip: " + clientIP);
        System.out.println("onthrow - request: " + request + ", exception: " + ex);
        exceptions.put(request, ex);
    }

    @Override
    public List<String> getInvokes() {
        return invokes;
    }

    @Override
    public Map<String, String> getReturns() {
        return returns;
    }

    @Override
    public Map<String, Throwable> getExceptions() {
        return exceptions;
    }
}
