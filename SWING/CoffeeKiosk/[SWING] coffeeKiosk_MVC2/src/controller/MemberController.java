package controller;

import javax.swing.JOptionPane;

import dto.MemberDto;
import service.MemberService;
import service.impl.MemberServiceImpl;
import singleton.Singleton;
import view.AccountView;
import view.CoffeeOrderView;
import view.LoginView;

public class MemberController {
	MemberService memServ = new MemberServiceImpl();
	
	public void login() {
		new LoginView();
	}
	
	public void regi() {
		new AccountView();
	}
	
	public boolean idCheck(String id) {
		return memServ.idCheck(id);
	}
	
	public void addMember(MemberDto dto) {
		boolean b = memServ.addMember(dto);
		if(b) {
			JOptionPane.showMessageDialog(null, "회원가입 성공");
			login();
		} else {
			JOptionPane.showMessageDialog(null, "회원가입 실패");
			regi();
		}
	}
	
	public void loginAF(String id, String pswd) {
		MemberDto dto = memServ.login(id, pswd);
		if(dto == null) {
			JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
			login();
		} else {
			JOptionPane.showMessageDialog(null, dto.getId()+"님, 환영합니다.");
			Singleton s = Singleton.getInstance();
			s.setLoginID(id);
			new CoffeeOrderView();
			
		}
	}
	
}
