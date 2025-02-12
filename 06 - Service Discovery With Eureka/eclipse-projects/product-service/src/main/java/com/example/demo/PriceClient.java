package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PRICING-SERVICE")
public interface PriceClient {
	@GetMapping("/price/{productid}")
	public Price getPriceDetails(@PathVariable("productid") Long productid);
}
