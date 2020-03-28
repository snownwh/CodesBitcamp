package main;

import db.DBConnection;
import singleton.Singleton;
import view.AccountView;
import view.CoffeeOrderView;
import view.CoffeePriceTableView;

public class MainClass {
	public static void main(String[] args) {
		DBConnection.initConnection();
		
		Singleton s = Singleton.getInstance();
		s.memCtrl.login();
//		s.memCtrl.regi();
//		s.cCtrl.getCoffeelist();
//		new orderView();
//		new CoffeeOrderView();
	}
}




