/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.api.gateway;

import java.util.function.*;
import org.springframework.cloud.gateway.route.*;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.*;

/**
 *
 * @author PC
 */
@Configuration
public class ApiGatewayConfiguration {
    
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(p -> p.path("/currency-converter/**")
                        .uri("lb://currency-conversion-service"))
                .route(p -> p.path("/currency-converter-feign/**")
                        .uri("lb://currency-conversion-service"))
                .route(p -> p.path("/currency-converter-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-converter-new/(?<segment>.*)",
                                "/currency-converter-feign/${segment}"))
                        .uri("lb://currency-conversion-service"))
                .build();
    }
}
