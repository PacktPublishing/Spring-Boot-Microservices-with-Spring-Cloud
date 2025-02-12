package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class InventoryController {
	
	private Logger logger = LoggerFactory.getLogger(InventoryController.class);

	public InventoryController() {
		populateInventoryList();
	}

	List<Inventory> inventoryList = new ArrayList<Inventory>();

	@GetMapping("/inventory/{productid}")
	public Mono<Inventory> getInventoryDetails(@PathVariable Long productid) {
		logger.info("get inventory details");
		return Mono.just(getInventoryInfo(productid));
	}

	private Inventory getInventoryInfo(Long productid) {

		for (Inventory i : inventoryList) {
			if (productid.equals(i.getProductID())) {
				return i;
			}
		}

		return null;
	}

	private void populateInventoryList() {
		inventoryList.clear();
		inventoryList.add(new Inventory(301L, 101l, true));
		inventoryList.add(new Inventory(302L, 102l, true));
		inventoryList.add(new Inventory(303L, 103l, false));
	}

}
