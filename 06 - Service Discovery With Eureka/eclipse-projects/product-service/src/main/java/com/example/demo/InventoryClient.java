package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service")
public interface InventoryClient {
	@GetMapping("/inventory/{productid}")
	public Inventory getInventoryDetails(@PathVariable("productid") Long productid);
}
