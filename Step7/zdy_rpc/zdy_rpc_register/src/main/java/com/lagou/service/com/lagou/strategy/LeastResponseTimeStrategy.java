package com.lagou.service.com.lagou.strategy;

import java.util.Map;
import java.util.Optional;

/**
 * @ClassName LeastResponseTimeStrategy
 * @Description
 * @Author decamincow
 * @Date 2020/4/30 8:03 AM
 * @Version 1.0
 **/
public class LeastResponseTimeStrategy extends AbstractLoadBalance {
    protected String doSelect(Map<String, Long> invokers) {

        Optional<Map.Entry<String, Long>> min = invokers.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue());

        return min.get().getKey();
    }
}
