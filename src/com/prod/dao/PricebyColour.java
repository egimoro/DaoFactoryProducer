package com.prod.dao;


public interface PricebyColour extends Dao{
	public void getColour(String colour);
	public double getDiscount(double rate);

}
