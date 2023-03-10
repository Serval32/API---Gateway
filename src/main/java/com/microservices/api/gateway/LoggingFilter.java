/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.api.gateway;


import org.slf4j.*;
import org.springframework.cloud.gateway.filter.*;
import org.springframework.web.server.*;
import reactor.core.publisher.*;
import org.springframework.stereotype.*;

/**
 *
 * @author PC
 */

@Component
public class LoggingFilter implements GlobalFilter{
    
    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
            GatewayFilterChain chain) {
        logger.info("Path of the request received -> {}",
                exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
    
}
