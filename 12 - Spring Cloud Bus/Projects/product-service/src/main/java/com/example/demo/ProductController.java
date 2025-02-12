package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RefreshScope
@RestController
public class ProductController {

	public ProductController() {

		populateProductList();
	}

	List<ProductInfo> productList = new ArrayList<ProductInfo>();

	WebClient webClient = WebClient.create();
	
	@Value("${password}")
	String password;
	
	@Value("${message}")
	String message;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/product/details/{productid}")
	public Mono<Product> getProductDetails(@PathVariable Long productid) {
		
		System.out.println("Password is : " + password);
		
		System.out.println("Message is : " + message);
		
		// Get Name and Desc from product-service
		Mono<ProductInfo> productInfo = Mono.just(getProductInfo(productid));
		
		// Get Price from pricing-service
		Mono<Price> price = webClient.get().uri("http://localhost:8002/price/{productid}", productid).retrieve()
				.bodyToMono(Price.class);

		// Get Stock Avail from inventory-service

		Mono<Inventory> inventory = webClient.get().uri("http://localhost:8003/inventory/{productid}", productid)
				.retrieve().bodyToMono(Inventory.class);

		return Mono.zip(productInfo, price, inventory)
				.map(tuple -> new Product(tuple.getT1().getProductID(), tuple.getT1().getProductName(),
						tuple.getT1().getProductDesc(), tuple.getT2().getDiscountedPrice(),
						tuple.getT3().getInStock()));
	}

	@GetMapping("/product/list")
	public Flux<Product> getProducts() {

		return Flux.fromStream(productList.stream()).flatMap(productInfo -> {

			// Get Price from pricing-service
			Mono<Price> price = webClient.get()
					.uri("http://localhost:8002/price/{productid}", productInfo.getProductID()).retrieve()
					.bodyToMono(Price.class);

			// Get Stock Avail from inventory-service

			Mono<Inventory> inventory = webClient.get()
					.uri("http://localhost:8003/inventory/{productid}", productInfo.getProductID()).retrieve()
					.bodyToMono(Inventory.class);

			return Mono.zip(price, inventory)
					.map(tuple -> new Product(productInfo.getProductID(), productInfo.getProductName(),
							productInfo.getProductDesc(), tuple.getT1().getDiscountedPrice(),
							tuple.getT2().getInStock()));

		});
	}

	private ProductInfo getProductInfo(Long productid) {

		for (ProductInfo p : productList) {
			if (productid.equals(p.getProductID())) {
				return p;
			}
		}

		return null;
	}

	private void populateProductList() {
		productList.clear();
		productList.add(new ProductInfo(101L, "iPhone", "iPhone is damn expensive!"));
		productList.add(new ProductInfo(102L, "Book", "Book is great!"));
		productList.add(new ProductInfo(103L, "Washing MC", "Washing MC is necessary"));
	}

}
