package controller;

import java.util.List;

import dto.CoffeeDto;
import service.CoffeeService;
import service.impl.CoffeeServiceImpl;
import view.CoffeePriceTableView;


public class CoffeeController {
	CoffeeService cServ = new CoffeeServiceImpl();
	
	public void getCoffeelist() {
		List<CoffeeDto>  list = cServ.getCoffeeList();
		new CoffeePriceTableView(list);
		
	}
}
