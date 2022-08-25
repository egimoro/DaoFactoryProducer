package com.prod.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.prod.entities.Hat;
import com.prod.entities.Product;

public class HatDao extends ProductDao implements Dao, PricebyColour {
	
	public HatDao(){};
	
	@Override
	public void create(Product product) {
		try {
			String query = "insert into hat (id,hasAccessories,size) values(?,?,?);";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			Hat hat = (Hat)product;
			ps.setInt(1, hat.getId());
			ps.setBoolean(2, hat.isHasAccessories());
			ps.setString(3, hat.getSize());
			ps.executeUpdate();
			System.out.println("Added.");
		}catch(SQLException e) {
			System.err.println("Can't insert. " + e);
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch(SQLException e) {
				System.err.println("Can't close. " + e);
			}
		}
		
	}

	@Override
	public List<Map<String, String>> read() {
		List<Map<String, String>>hats = new ArrayList<Map<String,String>>();
		try {
		String query = "\r\n"
				+ "select * from product \r\n"
				+ "inner join hat on product.id = hat.id"
				+ " where product.prodType = 'hat';";
		connection = getConnection();
		ps = connection.prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next()) {
			Map<String,String>hat = new LinkedHashMap<String, String>();
			for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				hat.put(rs.getMetaData().getColumnName(i), rs.getString(i));		
			}
			hats.add(hat);
			for(Map.Entry<String, String>entry : hat.entrySet()) {
				System.out.println("Key: " + entry.getKey()+" & "+"Value: "+entry.getValue());
			}
		}
		}catch(SQLException e) {
			System.err.println("Can't read. " + e);
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch(SQLException e) {
				System.err.println("Can't close. " + e);
			}
		}
		return hats;
	}

	@Override
	public void update(Product product) {
		try {
			String query = "update hat set size = ?, hasAccessories = ? where id = ?";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			Hat hat = (Hat)product;
			ps.setString(1, hat.getSize());
			ps.setBoolean(2, hat.isHasAccessories());
			ps.setInt(3, hat.getId());
			ps.executeUpdate();
			System.out.println("Updated successfully!");
		}catch(SQLException e) {
			System.err.println("Can't insert. " + e);
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch(SQLException e) {
				System.err.println("Can't close. " + e);
			}
		}
		
	}

	@Override
	public void delete(int id) {
		try {
			String query = "delete * from hat where id = ?;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			System.out.println("Data deleted successfully.");
		}catch(SQLException e) {
			System.err.println("Can't insert. " + e);
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch(SQLException e) {
				System.err.println("Can't close. " + e);
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
				Map<String, String>col = new LinkedHashMap<String, String>();
				for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					col.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
				}
				if(col.get("colour").equalsIgnoreCase("black") && 
						col.get("size").equalsIgnoreCase("small"))
					System.out.println("Price if black: " +Double.parseDouble(col.get("price")));
				else if(col.get("colour").equalsIgnoreCase("blue")&& 
						col.get("size").equalsIgnoreCase("large"))
					System.out.println("Price if blue: " + 
							Double.parseDouble(col.get("price")) * .85);
				else
					System.out.println("Can't operate on colour get size. " + col.get("size"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch(SQLException e) {
				System.err.println("Can't close. " + e);
			}
		}

			
	}
	
	@Override
	public double getDiscount(double rate) {
		try {
			String query = "select * from product\r\n"
					+ "inner join hat on product.id = hat.id\r\n"
					+ "where hat.size = \"small\" and product.colour ="
					+ " \"black\" and hat.hasAccessories = true;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String>col = new LinkedHashMap<String, String>();
				for(int i = 1; i<=rs.getMetaData().getColumnCount(); i++) {
					col.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
				}
				if(col.get("size").equalsIgnoreCase("small"))
					if(col.get("colour").equalsIgnoreCase("black"))
						rate += Double.parseDouble(col.get("price")) *.6;
					else if(Boolean.parseBoolean(col.get("hasAccessories")))
						rate += Double.parseDouble(col.get("price")) *.35;
					else
						System.out.println("No discount rate: ");
				System.out.println("Rate: " + rate);
			}
		}catch(SQLException e) {
			System.err.println("Can't insert. " + e);
		}finally {
			try {
				if(ps != null)
					ps.close();
				if (connection != null)
					connection.close();
			}catch(SQLException e) {
				System.err.println("Can't close. " + e);
			}
		}
		
		
		return rate;
	}

}
