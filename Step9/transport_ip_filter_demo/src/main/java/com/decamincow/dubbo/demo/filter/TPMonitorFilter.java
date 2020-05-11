package com.decamincow.dubbo.demo.filter;

import com.decamincow.dubbo.demo.Constants.TPConstans;
import com.decamincow.dubbo.demo.model.MyTime;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.rpc.*;

/**
 * @ClassName TPMonitorFilter
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/11 5:49 PM
 * @Version 1.0
 **/
@Activate(group = CommonConstants.CONSUMER)
public class TPMonitorFilter implements Filter {

    // 每隔5s打印一次最近1分钟内每个方法的TP90、TP99的耗时情况
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Long startTime = System.currentTimeMillis();
        Long endTime = null;
        try{
            RpcContext.getContext()
                    .setInvoker(invoker)
                    .setInvocation(invocation)
                    .setLocalAddress(NetUtils.getLocalHost(), 0)
                    .setRemoteAddress(invoker.getUrl().getHost(), invoker.getUrl().getPort());

            Result result;
            
            result = invoker.invoke(invocation);
            endTime = System.currentTimeMillis();
            return result;
        }finally {
            Long responseTime = endTime - startTime;
            switch (invocation.getMethodName()){
                case "sayHello":
                    TPConstans.SAY_HELLO_TP_LIST.add(MyTime.builder().recordTime(endTime).responseTime(responseTime).build());
                    break;
                case "greeting":
                    TPConstans.GREETING_TP_LIST.add(MyTime.builder().recordTime(endTime).responseTime(responseTime).build());
                    break;
                case "run":
                    TPConstans.RUN_TP_LIST.add(MyTime.builder().recordTime(endTime).responseTime(responseTime).build());
                    break;
            }
        }

    }

}
