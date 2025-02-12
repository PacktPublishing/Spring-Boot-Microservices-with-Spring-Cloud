package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Mono;

@RestController
public class PriceController {
	
	

	public PriceController() {
		populatepriceList();
	}

	@Autowired
	private RestTemplate restTemplate;

	List<Price> priceList = new ArrayList<Price>();

	@GetMapping("/price/{productid}")
	public Mono<Price> getPriceDetails(@PathVariable Long productid) {
		return Mono.just(getPriceInfo(productid));
	}

	private Price getPriceInfo(Long productid) {

		for (Price p : priceList) {
			if (productid.equals(p.getProductID())) {

				return p;
			}
		}

		return null;
	}

	private void populatepriceList() {
		priceList.clear();
		priceList.add(new Price(201L, 101l, 1999, 999));
		priceList.add(new Price(202L, 102l, 199, 19));
		priceList.add(new Price(203L, 103l, 1222, 600));
	}

}
