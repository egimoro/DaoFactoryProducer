package com.prod.entities;

public abstract class Product {
	private int id;
	private String prodType;
	private String model;
	private String brand;
	private double price;
	private String colour;
	
	public Product() {
		super();
	}
	public Product(int id, String prodType, String model, String brand, double price, String colour) {
		super();
		this.id = id;
		this.prodType = prodType;
		this.model = model;
		this.brand = brand;
		this.price = price;
		this.setColour(colour);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	
	

}
