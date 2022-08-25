package com.prod.dao;

import java.util.*;

import com.prod.entities.Product;

public interface Dao {
	public void create(Product product);
	public List<Map<String, String>>read();
	public void update(Product product);
	public void delete(int id);
	

}
