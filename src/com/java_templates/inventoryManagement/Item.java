package com.java_templates.inventoryManagement;
public class Item {
	private final String name;
	private double costPrice;
	private double sellingPrice;
	private int quantity;

	public Item(String name, double costPrice, double sellingPrice) {
		super();
		this.name = name;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
	}

	public String getName() {
		return name;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", costPrice=" + costPrice + ", sellingPrice=" + sellingPrice + "]";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}