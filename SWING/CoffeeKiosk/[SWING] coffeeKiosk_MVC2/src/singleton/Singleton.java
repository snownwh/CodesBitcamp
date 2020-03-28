package singleton;

import controller.CoffeeController;
import controller.MemberController;
import dto.MemberDto;

public class Singleton {
	private static Singleton s = null;
	public MemberController memCtrl= null;
	public MemberDto memDto=null;
	public CoffeeController cCtrl = null;
	
	private String loginID;

	public Singleton() {
		memCtrl = new MemberController();
		memDto = new MemberDto();
		cCtrl = new CoffeeController();
	}

	public static Singleton getInstance() {
		if (s == null) {
			s = new Singleton();
		}
		return s;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	
	
	
	
	
	
}
