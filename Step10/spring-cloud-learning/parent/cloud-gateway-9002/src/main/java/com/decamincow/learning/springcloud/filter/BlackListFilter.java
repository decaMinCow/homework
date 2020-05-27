package com.decamincow.learning.springcloud.filter;

/**
 * @ClassName BlackListFilter
 * @Description TODO
 * @Author decamincow
 * @Date 2020/5/26 1:11 PM
 * @Version 1.0
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义全局过滤器，会对所有路由⽣效
 */
@Slf4j
@Component  // 让容器扫描到，等同于注册了
public class BlackListFilter implements GlobalFilter, Ordered {
    // 模拟⿊名单（实际可以去数据库或者redis中查询）
    private static List<String> blackList = new ArrayList<>();
    static {
        blackList.add("0:0:0:0:0:0:0:1");  // 模拟本机地址
    }
    /**
     * 过滤器核⼼⽅法
     * @param exchange 封装了request和response对象的上下⽂
     * @param chain ⽹关过滤器链（包含全局过滤器和单路由过滤器）
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain
            chain) {
        // 思路：获取客户端ip，判断是否在⿊名单中，在的话就拒绝访问，不在的话就放⾏
        // 从上下⽂中取出request和response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 从request对象中获取客户端ip
        String clientIp = request.getRemoteAddress().getHostString();
        // 拿着clientIp去⿊名单中查询，存在的话就决绝访问
        if(blackList.contains(clientIp)) {
            // 决绝访问，返回
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 状态码
            log.debug("=====>IP:" + clientIp + " 在⿊名单中，将被拒绝访问！");
            String data = "Request be denied!";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
            return response.writeWith(Mono.just(wrap));
        }
        // 合法请求，放⾏，执⾏后续的过滤器
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