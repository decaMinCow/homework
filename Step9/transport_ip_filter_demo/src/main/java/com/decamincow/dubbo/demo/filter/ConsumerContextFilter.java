//package com.decamincow.dubbo.demo.filter;
//
//import org.apache.dubbo.common.constants.CommonConstants;
//import org.apache.dubbo.common.extension.Activate;
//import org.apache.dubbo.common.utils.NetUtils;
//import org.apache.dubbo.rpc.*;
//
///**
// * @ClassName ConsumerContextFilter
// * @Description TODO
// * @Author decamincow
// * @Date 2020/5/11 3:44 PM
// * @Version 1.0
// **/
//@Activate(group = CommonConstants.CONSUMER)
//public class ConsumerContextFilter implements Filter {
//
//    @Override
//    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        RpcContext.getContext()
//                .setInvoker(invoker)
//                .setInvocation(invocation)
//                .setLocalAddress(NetUtils.getLocalHost(), 0)
//                .setRemoteAddress(invoker.getUrl().getHost(), invoker.getUrl().getPort());
//        return invoker.invoke(invocation);
//    }
//}
