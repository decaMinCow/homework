//package com.decamincow.practice.filter.config;
//
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @ClassName HostAddrKeyResolver
// * @Description TODO
// * @Author decamincow
// * @Date 2020/5/27 6:40 PM
// * @Version 1.0
// **/
//public class HostAddrKeyResolver implements KeyResolver {
//
//    @Override
//    public Mono<String> resolve(ServerWebExchange exchange) {
//        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
//    }
//    @Bean
//    public HostAddrKeyResolver hostAddrKeyResolver() {
//        return new HostAddrKeyResolver();
//    }
//
//}
