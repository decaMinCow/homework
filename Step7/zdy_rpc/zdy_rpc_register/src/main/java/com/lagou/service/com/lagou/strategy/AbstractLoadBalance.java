package com.lagou.service.com.lagou.strategy;

import java.util.Map;

/**
 * @ClassName AbstractLoadBalance
 * @Description TODO
 * @Author decamincow
 * @Date 2020/4/30 7:52 AM
 * @Version 1.0
 **/
public abstract class AbstractLoadBalance implements LoadBalanceStrategy {

    public String selectHost(Map<String, Long> serviceDataMap) {
        if(serviceDataMap == null || serviceDataMap.size() == 0){
            return null;
        }
        if(serviceDataMap.size() == 1){
            final String[] result = new String[1];
            serviceDataMap.forEach((key, value) -> {
                result[0] = key;
            });
            return result[0];
        }
        return doSelect(serviceDataMap);
    }

    protected abstract String doSelect(Map<String, Long> invokers);
}
