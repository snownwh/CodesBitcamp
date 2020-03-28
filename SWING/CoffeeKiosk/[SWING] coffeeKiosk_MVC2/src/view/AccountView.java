package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.MemberDto;
import singleton.Singleton;

public class AccountView extends JFrame implements ActionListener {
	JTextField idTextF, nameTextF, emailTextF;
	JPasswordField passTextF;
	JButton idBtn, accountBtn, loginBtn;

	public AccountView() {
		super("회원가입");
		setLayout(null);

		JLabel loginLabel = new JLabel("회원가입 화면");
		loginLabel.setBounds(130, 10, 120, 15);
		add(loginLabel);

		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(31, 60, 67, 15);
		add(idLabel);

		idTextF = new JTextField();
		idTextF.setBounds(100, 60, 150, 20);
		add(idTextF);
		idTextF.setColumns(10);

		idBtn = new JButton("중복");
		idBtn.addActionListener(this);
		idBtn.setBounds(260, 60, 50, 20);
		add(idBtn);

		JLabel passLabel = new JLabel("비밀번호");
		passLabel.setBounds(31, 104, 67, 15);
		add(passLabel);

		passTextF = new JPasswordField();
		passTextF.setBounds(100, 104, 150, 20);
		add(passTextF);

		JLabel nameLabel = new JLabel("이름:");
		nameLabel.setBounds(31, 148, 67, 15);
		add(nameLabel);

		nameTextF = new JTextField();
		nameTextF.setBounds(100, 148, 150, 20);
		add(nameTextF);

		JLabel emailLabel = new JLabel("E-mail:");
		emailLabel.setBounds(31, 192, 67, 15);
		add(emailLabel);

		emailTextF = new JTextField();
		emailTextF.setBounds(100, 192, 150, 20);
		add(emailTextF);

		accountBtn = new JButton("회원가입");
		accountBtn.addActionListener(this);
		accountBtn.setBounds(31, 236, 280, 50);
		add(accountBtn);

		loginBtn = new JButton("돌아가기");
		loginBtn.addActionListener(this);
		loginBtn.setBounds(31, 300, 100, 20);
		add(loginBtn);

		setBounds(100, 500, 345, 380);
		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		// ID 중복 체크
		if (btn == idBtn) {
			Singleton s = Singleton.getInstance();
			boolean b = s.memCtrl.idCheck(idTextF.getText());
			if(idTextF.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "ID를 입력해 주세요.");
			} else {
				if (b == true) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.");
					idTextF.setText("");
				} else {
					JOptionPane.showMessageDialog(null, idTextF.getText() + "는 사용할 수 있는 ID니다.");
					return;
				}
			}
		//돌아가기 버튼
		} else if(btn == loginBtn) {
			Singleton s = Singleton.getInstance();
			s.memCtrl.login();
			this.dispose();
		
		// 회원가입 버튼
		} else if(btn == accountBtn) {
			if(idTextF.getText().equals("") || passTextF.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "ID 혹은 비밀번호를 기입해 주세요.");
			} else {
				MemberDto dto = new MemberDto();
				dto.setId(idTextF.getText());
				dto.setPwd(passTextF.getText());
				dto.setName(nameTextF.getText());
				dto.setEmail(emailTextF.getText());
				
				Singleton s = Singleton.getInstance();
				s.memDto = dto;
				
				s.memCtrl.addMember(s.memDto);
				
				this.dispose();	
			}
			
		}

	}

}
