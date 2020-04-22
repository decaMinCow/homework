package com.lagou.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @ClassName RpcRequest
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/22 3:51 PM
 * @Version 1.0
 **/

@Data
@Builder
public class RpcRequest{

    @Tolerate
    public RpcRequest() {
    }

    /**
     * 请求对象的ID
     */
    private String requestId;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;

    /**
     * 入参
     */
    private Object[] parameters;


}