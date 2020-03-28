package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.MemberDto;
import javabean.MemberDao;

public class loginView extends JFrame implements ActionListener {

	private JTextField idTextF;
	private JPasswordField pwTextF;
	
	private JButton logBtn;
	private JButton accountBtn;
	
	public loginView() {
		super("로그인");
		setLayout(null);
		
		JLabel loginLabel = new JLabel("로그인 화면");
		loginLabel.setBounds(100, 10, 120, 15);
		add(loginLabel);
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setBounds(31, 60, 67, 15);
		add(idLabel);
		
		idTextF = new JTextField(10);
		idTextF.setBounds(100, 60, 150, 20);
		add(idTextF);
		
		JLabel passLabel = new JLabel("PW:");
		passLabel.setBounds(31, 104, 67, 15);
		add(passLabel);
		
		pwTextF = new JPasswordField();
		pwTextF.setBounds(100, 104, 150, 20);
		add(pwTextF);
				
		logBtn = new JButton("log-in");
		logBtn.setBounds(31, 150, 100, 40);
		logBtn.addActionListener(this);
		add(logBtn);
		
		accountBtn = new JButton("회원가입");
		accountBtn.setBounds(150, 150, 100, 40);
		accountBtn.addActionListener(this);
		add(accountBtn);
		
		setBounds(100, 100, 300, 280);
		getContentPane().setBackground(Color.red);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {				
				System.exit(0);				
			}			
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		JButton btn = (JButton)e.getSource();
				
		MemberDao dao = MemberDao.getInstance();
		if(btn == logBtn){	// log in
			MemberDto mem = dao.login(idTextF.getText(), pwTextF.getText()); 
			if(mem == null) {
				JOptionPane.showMessageDialog(null, "id나 password가 틀렸습니다");
			}else {
				JOptionPane.showMessageDialog(null, mem.getId() + "님 환영합니다");
				this.dispose();
				
				// login한 id를 저장	-> Session(Web)
				dao.setLoginID(mem.getId());
				
				new bbsListView();
			}			
		}
		else if(btn == accountBtn){ // 회원가입
			new accountView();
			this.dispose();
		}
	}

}
