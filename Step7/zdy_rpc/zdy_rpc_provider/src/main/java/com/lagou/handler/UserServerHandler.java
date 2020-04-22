package com.lagou.handler;

import com.lagou.model.request.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class UserServerHandler extends ChannelInboundHandlerAdapter implements ApplicationContextAware {

    private static ApplicationContext applicationContext2;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        UserServerHandler.applicationContext2 = applicationContext;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest rpcRequest = (RpcRequest)msg;
        Object handler = handler(rpcRequest);
        System.out.println(rpcRequest);
        ctx.writeAndFlush("success");

    }

    private Object handler (RpcRequest request) throws ClassNotFoundException, InvocationTargetException {
        Class<?> clazz = Class.forName(request.getClassName());
        Object serviceBean = applicationContext2.getBean(clazz);
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        FastClass fastClass = FastClass.create(serviceClass);
        FastMethod fastMethod = fastClass.getMethod(methodName, parameterTypes);

        return fastMethod.invoke(serviceBean, parameters);

    }


}