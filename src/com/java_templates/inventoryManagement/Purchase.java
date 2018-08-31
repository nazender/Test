package com.java_templates.inventoryManagement;

public class Purchase {
	private final String itemName;
	private final double sellingPrice;
	private final int quantity;

	public Purchase(String itemName, double sellingPrice, int quantity) {
		super();
		this.itemName = itemName;
		this.sellingPrice = sellingPrice;
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public int getQuantity() {
		return quantity;
	}

}