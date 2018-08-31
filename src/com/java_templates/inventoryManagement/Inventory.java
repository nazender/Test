package com.java_templates.inventoryManagement;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private final List<Item> items;
	private final List<Purchase> purchases;
	private double loss;

	public List<Item> getItems() {
		return items;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public Inventory() {
		items = new ArrayList<>();
		purchases = new ArrayList<>();
	}

	public Item getItemByName(String name) {
		for (Item item : items) {
			if (item.getName().equals(name))
				return item;
		}
		return null;
	}

	public List<Purchase> getPurchasesByName(String name) {
		List<Purchase> purchases = new ArrayList<>();
		for (Purchase purchase : this.purchases) {
			if (purchase.getItemName().equals(name))
				purchases.add(purchase);
		}
		return purchases;
	}

	public void addNewItem(Item item) {
		this.items.add(item);
	}

	public void deleteItem(String itemName) {
		Item item = this.getItemByName(itemName);
		if (item != null) {
			this.items.remove(item);
			loss += (item.getCostPrice() * item.getQuantity());
		} else
			;//code if item does not exists
	}

	public void updateBuy(String itemName, int quantity) {
		Item item = this.getItemByName(itemName);
		if (item != null)
			item.setQuantity(item.getQuantity() + quantity);
		else
			;//code if item does not exists
	}

	public void updateSell(String itemName, int quantity) {
		Item item = this.getItemByName(itemName);
		if (item != null) {
			if (item.getQuantity() >= quantity) {
				item.setQuantity(item.getQuantity() - quantity);
				this.purchases.add(new Purchase(itemName, item.getSellingPrice(), quantity));
			} else
				;//code if current item is not available in required quantity
		} else
			;//code if item does not exists
	}

	public void report() {
		System.out.println("				INVENTORY REPORT");
		System.out.println(String.format("%s	%s	%s	%s	%s", "Item Name", "Bought At", "Sold At", "Available Quantity", "Value"));
		System.out.println(String.format("%s	%s	%s	%s	%s", "---------", "---------", "-------", "------------------", "-----"));
		double totalValue = 0d;
		double profit = 0d;
		for (Item item : this.items) {
			for (Purchase purchase : this.getPurchasesByName(item.getName())) {
				profit += ((purchase.getSellingPrice() - item.getCostPrice()) * purchase.getQuantity());
			}

			totalValue += item.getQuantity() * item.getCostPrice();
			System.out.println(String.format("%s		%.2f		%.2f		%d		%.2f", item.getName(), item.getCostPrice(), item.getSellingPrice(), item.getQuantity(),
					item.getQuantity() * item.getCostPrice()));

		}
		profit -= this.loss;
		System.out.println("------------------------------------------------------------------------------");
		System.out.println(String.format("%s	%s	%s	%s	%.2f", "Total Value", "         ", "       ", "                  ", totalValue));
		System.out.println(String.format("%s	%s	%s	%.2f", "Profit Since previous report", "       ", "                  ", profit));
		this.loss = 0;
		this.purchases.removeAll(this.purchases);
	}
}