package com.lagou.service.com.lagou.strategy;

import java.util.Map;

public interface LoadBalanceStrategy {

    String selectHost(Map<String, Long> serviceDataMap);
}
