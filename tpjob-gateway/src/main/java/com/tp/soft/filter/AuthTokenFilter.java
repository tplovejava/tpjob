package com.tp.soft.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthTokenFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getURI().toString();
        //System.out.println(requestUrl);
        //请求白名单
        if (requestUrl.contains("SSOAuth") || requestUrl.contains("TokenCenter") || requestUrl.contains("upload/")
                || requestUrl.contains("login")) {
            return chain.filter(exchange);
        }
        String warningStr = null;
        String jwtToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        //校验jwtToken的合法性
        if (StringUtils.isNotBlank(jwtToken)) {
            warningStr = "校验成功";

            return chain.filter(exchange);
        } else {
            warningStr = "校验失败";

        }

        //不合法(响应未登录的异常)
        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(warningStr.getBytes());

        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
