package service.impl;

import java.util.List;

import dao.CoffeeDao;
import dao.impl.CoffeeDaoImpl;
import dto.CoffeeDto;
import service.CoffeeService;

public class CoffeeServiceImpl implements CoffeeService{
	CoffeeDao cDao = new CoffeeDaoImpl();

	@Override
	public List<CoffeeDto> getCoffeeList() {
		return cDao.getCoffeeList();
	}
	
	
	
}
