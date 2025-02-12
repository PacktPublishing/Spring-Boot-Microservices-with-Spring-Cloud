package com.example.demo;

public class Product {

	public Product() {
		super();
	}

	public Product(Long productID, String productName, String productDesc, Integer productPrice, Boolean productStock) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.productStock = productStock;
	}

	private Long productID;
	private String productName;
	private String productDesc;
	private Integer productPrice;
	private Boolean productStock;

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Boolean getProductStock() {
		return productStock;
	}

	public void setProductStock(Boolean productStock) {
		this.productStock = productStock;
	}

}
