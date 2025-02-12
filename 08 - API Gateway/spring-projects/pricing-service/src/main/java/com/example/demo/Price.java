package com.example.demo;

public class Price {
	
	public Price() {
		super();
	}
	
	public Price(Long priceID, Long productID, Integer originalPrice, Integer discountedPrice) {
		super();
		this.priceID = priceID;
		this.productID = productID;
		this.originalPrice = originalPrice;
		this.discountedPrice = discountedPrice;
	}
	
	private Long priceID;
	private Long productID;
	private Integer originalPrice;
	private Integer discountedPrice;
	public Long getPriceID() {
		return priceID;
	}

	public void setPriceID(Long priceID) {
		this.priceID = priceID;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Integer discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	
}
