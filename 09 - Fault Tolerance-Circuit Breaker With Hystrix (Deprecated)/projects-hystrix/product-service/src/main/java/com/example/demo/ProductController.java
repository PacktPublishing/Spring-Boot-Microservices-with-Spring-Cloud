package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class ProductController {

	List<ProductInfo> productList = new ArrayList<ProductInfo>();
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PriceClient priceClient;

	@Autowired
	private InventoryClient inventoryClient;

	@GetMapping("/getport")
	public String getProt() {
		return inventoryClient.getProtInfo();
	};

	@GetMapping("/product/details/{productid}")
	@HystrixCommand(fallbackMethod="fallbackMethod")
	@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")
	public Product getProductDetails(@PathVariable Long productid) {

		// Get Name and Desc from product-service
		ProductInfo productInfo = getProductInfo(productid);

		// Get Price from pricing-service
		Price price = restTemplate.getForObject("http://localhost:9001/price/"+productid, Price.class);

		// Get Stock Avail from inventory-service
		Inventory inventory = restTemplate.getForObject("http://localhost:9001/inventory/"+productid, Inventory.class);

		return new Product(productInfo.getProductID(), productInfo.getProductName(), productInfo.getProductDesc(),
				price.getDiscountedPrice(), inventory.getInStock());
	}
	
	
	public Product fallbackMethod(Long productid) {
		
		System.out.println("Entered the fallback mehtod..");
		return new Product();
	}
	
	
	private ProductInfo getProductInfo(Long productid) {
		populateProductList();

		for (ProductInfo p : productList) {
			if (productid.equals(p.getProductID())) {
				return p;
			}
		}

		return null;
	}

	private void populateProductList() {
		productList.add(new ProductInfo(101L, "iPhone", "iPhone is damn expensive!"));
		productList.add(new ProductInfo(102L, "Book", "Book is great!"));
		productList.add(new ProductInfo(103L, "Washing MC", "Washing MC is necessary"));
	}

}
