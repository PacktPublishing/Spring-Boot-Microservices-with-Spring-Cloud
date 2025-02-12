package com.example.demo;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.MyCustomFilter.Config;

@Configuration
public class SpringCloudConfig {

	@Bean
	public RouteLocator buildRouteLocator(RouteLocatorBuilder builder, MyCustomFilter myCustomFilter) {
	
		return builder.routes().route(r -> r.path("/price/**")
				.filters(f -> f.filter(myCustomFilter.apply(new Config())))
				.uri("http://localhost:8002"))
				.route(r -> r.path("/inventory/**").uri("http://localhost:8003")).build();
	}
}
