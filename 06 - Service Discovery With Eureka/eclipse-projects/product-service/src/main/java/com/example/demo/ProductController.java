package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@RestController
public class ProductController {

	List<ProductInfo> productList = new ArrayList<ProductInfo>();

	@Autowired
	private PriceClient priceClient;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private InventoryClient inventoryClient;

	@GetMapping("/product/details/{productid}")
	public Product getProductDetails(@PathVariable Long productid) {
				
		// Get Name and Desc from product-service
		ProductInfo productInfo = getProductInfo(productid);

		// Get Price from pricing-service
		Price price = priceClient.getPriceDetails(productid);

		// Get Stock Avail from inventory-service
		Inventory inventory = inventoryClient.getInventoryDetails(productid);

		return new Product(productInfo.getProductID(), productInfo.getProductName(), productInfo.getProductDesc(),
				price.getDiscountedPrice(), inventory.getInStock());
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
