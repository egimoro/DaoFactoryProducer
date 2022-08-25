package com.prod.dao;

import java.util.Scanner;

import com.prod.entities.Furniture;
import com.prod.entities.Hat;
import java.util.*;

public class ProductFactory extends AbstractFactory {

	@Override
	public Dao getProduct(String prodType) {
		Scanner scan = new Scanner(System.in);
		
		int choice = 0;
		if(prodType.equalsIgnoreCase("hat")) {
			Hat hat = new Hat();
			HatDao hatDao = new HatDao();
			do {
				System.out.println("1) create \n2) read \n3) "
				+ "update \n4) delete \n5) Get colour \n6) Get discount \n7) exit");
				choice = Integer.parseInt(scan.nextLine());
				switch(choice) {
				case 1:
					hat.setId(5);
					hat.setSize("small");
					hat.setHasAccessories(true);
					hatDao.create(hat);
					break;
				case 2:
					hatDao.read();
					break;
				case 3:
					hat.setSize("large");
					hat.setHasAccessories(true);
					hat.setId(1);
					hatDao.update(hat);
					break;
				case 4:
					hatDao.delete(1);
					break;
				case 5:
					String colour = "";
					System.out.println("Insert colour");
					colour = scan.nextLine();
					String query = "select * from product inner join hat on product.id = "
							+ "hat.id where product.colour ="+"colour"+";";
					
					hatDao.getColour(query);
					break;
				case 6:
					hatDao.getDiscount(0.25);
					break;
				case 7:
					System.out.println("Bye.");
					break;
				default:
					System.out.println("Wrong choice.");
				}	
			}while(choice != 7);
			return new HatDao();
		}else if(prodType.equalsIgnoreCase("furniture")) {
			Furniture furniture = new Furniture();
			FurnitureDao furnitureDao = new FurnitureDao();
			do {
				System.out.println("1) create \n2) read \n3) "
				+ "update \n4) delete \n5) Get brand by material \n6)"
				+ " Get room by colour \n7) exit");
				choice = Integer.parseInt(scan.nextLine());
				switch(choice) {
				case 1:
					furniture.setId(6);
					furniture.setFurnType("chair");
					furniture.setRoom("office");
					furniture.setMaterial("metal");
					furnitureDao.create(furniture);
					break;
				case 2:
					for(Map<String, String>furn : furnitureDao.read()) {
						if(furn.get("furnType").equalsIgnoreCase("table")
						|| furn.get("furnType").equalsIgnoreCase("chair")
						|| furn.get("furnType").equalsIgnoreCase("bed")
						|| furn.get("furnType").equalsIgnoreCase("sofa")) {
						for (Map.Entry<String, String>entry : furn.entrySet()) {
							System.out.println("Key: " + entry.getKey() +" & "+
									"Value: " + entry.getValue());
						}
						}
					}
					break;
				case 3:
					furniture.setFurnType("chair");
					furniture.setRoom("office");
					furniture.setMaterial("rattan");	
					furniture.setId(2);
					furnitureDao.update(furniture);
					break;
				case 4:
					furnitureDao.delete(2);
					break;
				case 5:
					String query = "select * from product\r\n"
							+ "inner join furniture on product.id = furniture.id\r\n"
							+ "where furniture.material = 'engineered wood';\r\n"
							+ "";
					furnitureDao.getBrandByMaterial(query);
					break;
				case 6:
					String colour;
					System.out.println("Insert colour");
					colour = scan.nextLine();
					String query1 = "select * from product inner join furniture on product.id = "
							+ "furniture.id where product.colour ="+"colour"+";";
					furnitureDao.getRoomByColour(query1);
					break;
				case 7:
					System.out.println("Bye.");
				}	
			}while(choice != 7);
			return new FurnitureDao();
		}
		return null;
	}

}
