package com.decamincow.practice.filter.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @ClassName HostAddrKeyResolver
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/27 6:22 PM
 * @Version 1.0
 **/
@Component
public class Config {

    @Bean
    KeyResolver ipKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

}

