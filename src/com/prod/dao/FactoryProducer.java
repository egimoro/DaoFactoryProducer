package com.prod.dao;

public class FactoryProducer {
	public static AbstractFactory getFactory(boolean isbase) {
		if(isbase) 
			return new ProductBaseFactory();
		else 
			return new ProductFactory();
		
	}

	

}
