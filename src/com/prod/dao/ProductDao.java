package com.prod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.prod.configuration.ConnectionFactory;
import com.prod.entities.Product;

public class ProductDao implements Dao, PricebyColour{
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ProductDao() {}
	
	protected Connection getConnection() {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public void create(Product product) {
		try {
		String query = "insert into product "
				+ "(prodType,model,brand,price,colour) values (?,?,?,?,?); ";
		connection = getConnection();
		ps = connection.prepareStatement(query);
		ps.setString(1, product.getProdType());
		ps.setString(2, product.getModel());
		ps.setString(3, product.getBrand());
		ps.setDouble(4, product.getPrice());
		ps.setString(5, product.getColour());
		ps.executeUpdate();
		System.out.println("Product added!");
		}catch(SQLException e) {
			System.err.println("Can't insert." + e);
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println("Can't shut connection. " + e);
			}
		}
		
	}


	@Override
	public List<Map<String, String>> read() {
		List<Map<String,String>>products = new ArrayList<Map<String,String>>();
		try {
		String query = "select * from product;";
		connection = getConnection();
		ps = connection.prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next()) {
			Map<String,String>product = new LinkedHashMap<String, String>();
			for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				product.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			}
			products.add(product);	
			
		}
		}catch(SQLException e) {
			System.err.println("Can't read " + e);
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println("Can't shut connection. " + e);
			}
		}
		return products;
	}

	@Override
	public void update(Product product) {
		try {
			String query = "update product set prodType = ?,  model = ?,  brand = ?, "
					+ "price = ?, colour = ? where id = ?;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, product.getProdType());
			ps.setString(2, product.getModel());
			ps.setString(3, product.getBrand());
			ps.setDouble(4, product.getPrice());
			ps.setString(5, product.getColour());
			ps.setInt(6, product.getId());
			ps.executeUpdate();
			System.out.println("Updated!");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println("Can't shut connection. " + e);
			}
		}
		
	}

	@Override
	public void delete(int id) {
		try {
			String query = "delete from product where id = ?;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Data deleted successfully.");
		}catch (SQLException e) {
			System.err.println("Can't update: " + e);
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println("Can't shut connection. " + e);
			}
		}
		
	}
	
	@Override
	public void getColour(String colour) {
		try {
			connection = getConnection();
			ps = connection.prepareStatement(colour);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String,String>col = new HashMap<String,String>();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					col.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				if (col.get("colour").equalsIgnoreCase("black"))
					System.out.println("Price: "+col.get("price"));
				else if(col.get("colour").equalsIgnoreCase("blue"))
					System.out.println("Price blue: "+Double.parseDouble(col.get("price"))+3.02);
				else
					System.out.println("No colour found.");
			}
		}catch (SQLException e) {
			 e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println("Can't shut connection. " + e);
			}
		}
		
	}

	@Override
	public double getDiscount(double rate) {
		try {
			String query = "select * from product;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String,String>product = new LinkedHashMap<String, String>();
				for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					product.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
				}
				if(product.get("colour").equalsIgnoreCase("black"))
					rate =rate * .20;
				else if(product.get("colour").equalsIgnoreCase("blue"))
					rate = rate * .25;
				else
					System.out.println("No discount.");
				System.out.println("Discount rate. " + rate);
			}
		}catch (SQLException e) {
			System.err.println("Can't update: " + e);
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println("Can't shut connection. " + e);
			}
		}
		
		return rate;
	}

}
