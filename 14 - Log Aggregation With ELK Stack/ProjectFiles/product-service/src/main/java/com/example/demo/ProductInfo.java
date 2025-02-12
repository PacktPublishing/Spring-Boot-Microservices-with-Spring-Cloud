package com.example.demo;

public class ProductInfo {

	public ProductInfo() {
		super();
	}

	public ProductInfo(Long productID, String productName, String productDesc) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productDesc = productDesc;
	}

	private Long productID;
	private String productName;
	private String productDesc;

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

}
