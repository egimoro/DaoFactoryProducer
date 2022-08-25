package com.prod.entities;

public class Hat extends Product {
	private boolean hasAccessories;
	private String size;
	
	public Hat(int id, String prodType, String model, String brand, double price, 
			String colour,boolean hasAccessories, String size) {
		super(id, prodType, model, brand, price, colour);
		this.size = size;
		this.setHasAccessories(hasAccessories);
	}
	
	public Hat() {
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}



	public boolean isHasAccessories() {
		return hasAccessories;
	}



	public void setHasAccessories(boolean hasAccessories) {
		this.hasAccessories = hasAccessories;
	}

	

	

}
