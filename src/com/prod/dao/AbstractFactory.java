package com.prod.dao;

public abstract class AbstractFactory {
	public abstract Dao getProduct(String prodType);

}
