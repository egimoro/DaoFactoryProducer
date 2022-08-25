package com.prod.configuration;
import java.sql.*;
import java.util.*;
import java.io.*;

public class ConnectionFactory {
	static String driverClassName = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/";
	static String username = null;
	static String password = null;
	static String database = null;
	
	private static ConnectionFactory connectionFactory = null;
	
	private ConnectionFactory() {
		try {
			Class.forName(driverClassName);	
		}catch(ClassNotFoundException e) {
			System.err.println("Can't get class. " + e);
		}
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try (InputStream input = new FileInputStream("res/conf.properties")) {
			Properties properties = new Properties();
			properties.load(input);
			
			username = properties.getProperty("username");
			password = properties.getProperty("password"); 
			database = properties.getProperty("database")+ "?";
			conn = DriverManager.getConnection(url+database, username, password);		
			
		}catch(IOException | SQLException e) {
			System.err.println("Can't read properties " + e);
		}
		return conn;
	}
	
	public static ConnectionFactory getInstance() {
		if(connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
	

}
