package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import singleton.Singleton;

public class LoginView extends JFrame implements ActionListener{
	JTextField idTextF;
	JPasswordField pswTextF;
	JButton logBtn, accountBtn;
	
	
	public LoginView() {
		super("로그인");
		setLayout(null);
		
		JLabel loginLabel = new JLabel("커피 주문 프로그램");
		loginLabel.setBounds(100, 10, 120, 15);
		add(loginLabel);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(30, 60, 67, 25);
		add(idLabel);
		
		idTextF = new JTextField();
		idTextF.setBounds(80, 60, 110, 25);
		add(idTextF);
		
		JLabel passLabel = new JLabel("비밀번호");
		passLabel.setBounds(30, 95, 67, 25);
		add(passLabel);
		
		logBtn = new JButton("로그인");
		logBtn.setBounds(200, 60, 70, 58);
		logBtn.addActionListener(this);
		add(logBtn);
		
		accountBtn = new JButton("회원가입");
		accountBtn.setBounds(30, 140, 238, 40);
		accountBtn.addActionListener(this);
		add(accountBtn);
		
		pswTextF = new JPasswordField();
		pswTextF.setBounds(80, 95, 110, 25);
		add(pswTextF);
		
		setBounds(100, 500, 310, 250);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn == accountBtn) {
			Singleton s = Singleton.getInstance();
			s.memCtrl.regi();
			this.dispose();
		} else if(btn == logBtn) {
			Singleton s = Singleton.getInstance();
			s.memCtrl.loginAF(idTextF.getText(), pswTextF.getText());
			new CoffeeOrderView();
			this.dispose();
		}
		
		
	}
	
	
	
	
	
	
	
	
}
