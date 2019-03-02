package com.tp.soft.filter;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

public class UserTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<UserTokenGatewayFilterFactory.Config>  {



    public UserTokenGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String requestUrl = exchange.getRequest().getURI().toString();
            if (requestUrl.contains("SSOAuth") || requestUrl.contains("TokenCenter") || requestUrl.contains("upload/")
                    || requestUrl.contains("login")) {
                return chain.filter(exchange);
            }

            ServerHttpResponse response = exchange.getResponse();
            String userToken = exchange.getRequest().getHeaders().getFirst("UserToken");



            ServerHttpRequest host = exchange.getRequest().mutate()
                    .header("clientType", "pc")
                    .header("userInfo", "123")
                    .build();
            //将现在的request 变成 change对象
            ServerWebExchange build = exchange.mutate().request(host).build();

            return chain.filter(build);
        };
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }
}
