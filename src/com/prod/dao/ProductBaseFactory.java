package com.prod.dao;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.prod.entities.Furniture;
import com.prod.entities.Hat;
import com.prod.entities.Product;

public class ProductBaseFactory extends AbstractFactory {

	@Override
	public Dao getProduct(String prodType) {
		Scanner scan = new Scanner(System.in);
		
		int choice = 0;
		if(prodType.equalsIgnoreCase("hat")) {
			Product product = new Hat();
			ProductDao productDao = new ProductDao();

			do {
				System.out.println("1) create \n2) read \n3) "
						+ "update \n4) delete \n5) exit");
				choice = Integer.parseInt(scan.nextLine());
				switch(choice) {
				case 1:
					
					product.setProdType("hat");
					product.setModel("Fedora 9");
					product.setBrand("Fedora");
					product.setPrice(18.26);
					product.setColour("black");
						productDao.create(product);
					
					break;
				case 2:
					for(Map<String, String>prod1 : productDao.read()) {
						if(prod1.get("prodType").equalsIgnoreCase("hat")) {
							for(Map.Entry<String, String>entry : prod1.entrySet()) {
								System.out.println("Key: " + entry.getKey() +" & "+
										"Value: " + entry.getValue());
							}
						}
					}					
					break;
				case 3:
					product.setProdType("hat");
					product.setModel("Bowler hat");
					product.setBrand("Fedora");
					product.setPrice(19.29);
					product.setColour("blue");
					product.setId(1);
					productDao.update(product);
					break;
				case 4:
					productDao.delete(2);
					break;
				case 5:
					System.out.println("Bye.");
					break;
				default:
					System.out.println("Wrong choice.");
				}
				
			}while(choice != 5);

		}else if(prodType.equalsIgnoreCase("furniture")) {
			Product product1 = new Furniture();
			ProductDao productDao1 = new ProductDao();
			do {
				System.out.println("1) create \n2) read \n3) "
						+ "update \n4) delete \n5) exit");
				choice = Integer.parseInt(scan.nextLine());
				switch(choice) {
				case 1:
					product1.setProdType("furniture");
					product1.setModel("Fedorìna 9");
					product1.setBrand("Fedorìna");
					product1.setPrice(18.26);
					product1.setColour("green");
					productDao1.create(product1);
					break;
				case 2:
					
					for(Map<String, String>prod : productDao1.read()) {
						if(prod.get("prodType").equalsIgnoreCase("furniture")) {
							for(Map.Entry<String, String>entry : prod.entrySet()) {
								System.out.println("Key: " + entry.getKey() +" & "+
										"Value: " + entry.getValue());
							}
						}
					}

					break;
				case 3:
					product1.setProdType("furniture");
					product1.setModel("Bellini 53");
					product1.setBrand("Bellini");
					product1.setPrice(335.10);
					product1.setColour("blue");
					product1.setId(1);
					productDao1.update(product1);
					break;
				case 4:
					productDao1.delete(2);
					break;
				case 5:
					System.out.println("Bye.");
					break;
				default:
					System.out.println("Wrong choice.");
				}
				
			}while(choice != 5);
			
		}
		
		return new ProductDao();
	}
	
	
}
