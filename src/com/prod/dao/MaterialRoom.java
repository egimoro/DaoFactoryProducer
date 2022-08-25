package com.prod.dao;

public interface MaterialRoom extends Dao{
	public void getRoomByColour(String colour);
	public void getBrandByMaterial(String material);

}
