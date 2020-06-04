package com.decamincow.practice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @ClassName CheckTokenFilter
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/28 1:38 AM
 * @Version 1.0
 **/
@Slf4j
@Component
public class CheckTokenFilter implements GlobalFilter, Ordered {
    /**
     * 过滤器核⼼⽅法
     * @param exchange 封装了request和response对象的上下⽂
     * @param chain ⽹关过滤器链（包含全局过滤器和单路由过滤器）
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain
            chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        System.out.println(request.getPath().contextPath().value());
//        boolean authorized = true;
//        if(request.getPath().contextPath().value() != "user" && request.getPath().contextPath().value() != "code" && request.getPath().contextPath().value() != "email") {
//            List<HttpCookie> tokens = request.getCookies().get("token");
//            tokens.stream().forEach(token -> {
//                // TODO 这里判断 token 赋值 authorized
//            });
//        }
//        if(!authorized){
//            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 状态码
//            String data = "Request be denied!";
//            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
//            return response.writeWith(Mono.just(wrap));
//        }
        return chain.filter(exchange);
    }
    /**
     * 返回值表示当前过滤器的顺序(优先级)，数值越⼩，优先级越⾼
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}