package com.tp.soft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayRunMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayRunMain.class, args);
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
//        config.setParts(1);
//        return builder.routes()
//                .route("host_route", r -> r.path("/a/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:7000"))
//                .route("host_route", r -> r.path("/b/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8082"))
//                .build();
//    }

}
