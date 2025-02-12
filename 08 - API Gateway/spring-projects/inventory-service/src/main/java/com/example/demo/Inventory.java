package com.example.demo;

public class Inventory {
	
	public Inventory() {
		super();
	}
	
	public Inventory(Long inventoryID, Long productID, Boolean inStock) {
		super();
		this.inventoryID = inventoryID;
		this.productID = productID;
		this.inStock = inStock;
	}
	
	private Long inventoryID;
	private Long productID;
	private Boolean inStock;
	public Long getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(Long inventoryID) {
		this.inventoryID = inventoryID;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}
	
}
