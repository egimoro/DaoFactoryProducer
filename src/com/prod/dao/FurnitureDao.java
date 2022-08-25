package com.prod.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.prod.entities.Furniture;
import com.prod.entities.Product;

public class FurnitureDao extends ProductDao implements Dao, MaterialRoom{
	
	public FurnitureDao() {}
	
	@Override
	public void create(Product product) {
		try {
			String query = "insert into furniture "
					+ "(id,furnType,room,material) values (?,?,?,?);";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			Furniture furniture = (Furniture)product;
			ps.setInt(1, furniture.getId());
			ps.setString(2, furniture.getFurnType());
			ps.setString(3, furniture.getRoom());
			ps.setString(4, furniture.getMaterial());
			ps.executeUpdate();
			System.out.println("Furniture installed!");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if(connection != null)
					connection.close();		
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public List<Map<String, String>>read(){
		List<Map<String, String>>furnitures = new ArrayList<Map<String,String>>();
		try {
			String query = "select * from product "
					+ "inner join furniture on product.id = furniture.id"
					+ " where product.prodType = 'furniture';";
			
			connection = getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String>furniture = new LinkedHashMap<String, String>();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					furniture.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
				}
				furnitures.add(furniture);
					
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if(connection != null)
					connection.close();		
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return furnitures;	
	}
	
	@Override
	public void update(Product product) {
		try {
			String query = "update furniture "
					+ "set furnType = ?, room = ?, material = ? where id = ?;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			Furniture furniture = (Furniture)product;
			ps.setString(1, furniture.getFurnType());
			ps.setString(2, furniture.getRoom());
			ps.setString(3, furniture.getMaterial());
			ps.setInt(4, furniture.getId());
			ps.executeUpdate();
			System.out.println("Furniture updated.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if(connection != null)
					connection.close();		
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void delete(int id){
		try {
			String query = "delete from furniture where id = ?;";
			connection = getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Furniture dismantled.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if(connection != null)
					connection.close();		
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void getRoomByColour(String colour) {
		try {
			connection = getConnection();
			ps = connection.prepareStatement(colour);
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String>col = new LinkedHashMap<String, String>();
				for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					col.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
				}
				if(col.get("colour").equalsIgnoreCase("blue") && 
						col.get("prodType").equalsIgnoreCase("furniture") )
					System.out.println("Room: " + col.get("room") );
				else
					System.out.println("Room not found.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if(connection != null)
					connection.close();		
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void getBrandByMaterial(String material) {
		try {
			connection = getConnection();
			ps = connection.prepareStatement(material);
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String>mat = new LinkedHashMap<String, String>();
				for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					mat.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
				}
				if(mat.get("material").equalsIgnoreCase("engineered wood") && 
						mat.get("prodType").equalsIgnoreCase("furniture") )
					System.out.println("Brand: "+mat.get("brand") +"\nPrice: "
				+ Double.parseDouble(mat.get("price")));
				else if(mat.get("material").equalsIgnoreCase("rattan"))
					System.out.println("Furniture type: " + mat.get("furnType"));
				else
					System.out.println("Can't find material.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
				if(connection != null)
					connection.close();		
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}


}
