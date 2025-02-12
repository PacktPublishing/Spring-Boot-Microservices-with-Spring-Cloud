package com.example.demo;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

	@Bean
	public RouteLocator buildRouteLocator(RouteLocatorBuilder builder) {
	
		return builder.routes().route(r -> r.path("/price/**")
				.uri("http://localhost:8002"))
				.route(r -> r.path("/inventory/**").uri("http://localhost:8003")).build();
	}
}
