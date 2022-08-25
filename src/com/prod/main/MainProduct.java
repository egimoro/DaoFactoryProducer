package com.prod.main;


import java.util.Scanner;

import com.prod.dao.AbstractFactory;
import com.prod.dao.Dao;
import com.prod.dao.FactoryProducer;

public class MainProduct {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		boolean isbase = false;
		String prodType = "";
		
		String ans = "";
		do {
		System.out.println("Is it a base factory? true ");
		isbase = Boolean.parseBoolean(scan.nextLine());
		if(isbase) {
			AbstractFactory prodFactory = FactoryProducer.getFactory(isbase);
			System.out.println("Hat or Furniture?");
			prodType = scan.nextLine();
			if (prodType.equalsIgnoreCase("hat")) {
				Dao dao = prodFactory.getProduct(prodType);
			}else {
				Dao dao = prodFactory.getProduct(prodType);
			}
		}else {
			AbstractFactory prodFactory = FactoryProducer.getFactory(isbase);
			System.out.println("Hat or Furniture?");
			prodType = scan.nextLine();
			if (prodType.equalsIgnoreCase("hat")) {
				Dao dao = prodFactory.getProduct(prodType);
			}else {
				Dao dao = prodFactory.getProduct(prodType);
			}
			
		}
		System.out.println("Would you like to continue? Yes/y or No/n");
		ans = scan.nextLine();
		}while(ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y"));
		
	}

}
