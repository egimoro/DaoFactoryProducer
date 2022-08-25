package com.prod.entities;

public class Furniture extends Product{
	private String furnType;
	private String room;
	private String material;
	
	public Furniture() {
		super();
	}
	public Furniture(int id, String prodType, String model, String brand, double price, String furnType, String room,
			String material) {
		super(id, prodType, model, brand, price, material);
		this.furnType = furnType;
		this.room = room;
		this.material = material;
	}
	public String getFurnType() {
		return furnType;
	}
	public void setFurnType(String furnType) {
		this.furnType = furnType;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	
	

}
