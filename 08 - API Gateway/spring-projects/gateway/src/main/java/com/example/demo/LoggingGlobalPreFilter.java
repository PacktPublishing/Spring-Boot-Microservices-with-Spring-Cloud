package com.example.demo;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingGlobalPreFilter implements GlobalFilter {
 
 
    @Override
    public Mono<Void> filter(
      ServerWebExchange exchange,
      GatewayFilterChain chain) {
        System.out.println("Global Pre Filter executed");
        return chain.filter(exchange);
    }
}