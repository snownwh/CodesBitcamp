package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.BbsDto;
import dto.MemberDto;
import javabean.BbsDao;
import javabean.MemberDao;

public class bbsDetail extends JFrame implements ActionListener {
	private JTextField writerTextF;
	private JTextField wdateTextF;
	private JTextField countTextF;
	private JTextField titleTextF;
	private JTextArea contentsTextF;

	private JButton updateBtn;
	private JButton deleteBtn;

	public bbsDetail(int seq) {
		super("글쓰기");
		setLayout(null);

		BbsDao bDao = BbsDao.getInstance();
		BbsDto bDto = bDao.getBBS(seq);
		bDao.readCount(seq);
		MemberDao dao = MemberDao.getInstance();

		JLabel wLabel = new JLabel("");
		wLabel.setBounds(100, 10, 120, 15);
		add(wLabel);

		JLabel writerLabel = new JLabel("작성자:");
		writerLabel.setBounds(31, 60, 67, 15);
		add(writerLabel);
		
		writerTextF = new JTextField(bDto.getId());
		writerTextF.setBounds(100, 60, 150, 20);
		writerTextF.setEditable(false);
		add(writerTextF);
		writerTextF.setColumns(10);

		JLabel wdateLabel = new JLabel("작성일:");
		wdateLabel.setBounds(31, 104, 67, 15);
		add(wdateLabel);

		wdateTextF = new JTextField(bDto.getWdate());
		wdateTextF.setBounds(100, 104, 150, 20);
		wdateTextF.setEditable(false);
		add(wdateTextF);

		JLabel countLabel = new JLabel("조회수");
		countLabel.setBounds(31, 148, 67, 15);
		add(countLabel);

		countTextF = new JTextField(bDto.getReadcount() + "");
		countTextF.setBounds(100, 148, 150, 20);
		countTextF.setEditable(false);
		add(countTextF);

		JLabel titleLabel = new JLabel("제목:");
		titleLabel.setBounds(31, 192, 67, 15);
		add(titleLabel);

		titleTextF = new JTextField(bDto.getTitle());
		titleTextF.setBounds(100, 192, 150, 20);
		titleTextF.setEditable(false);
		add(titleTextF);

		JLabel contentsLabel = new JLabel("내용:");
		contentsLabel.setBounds(31, 236, 67, 15);
		add(contentsLabel);

		contentsTextF = new JTextArea(bDto.getContent());
		contentsTextF.setEditable(false);
		contentsTextF.setLineWrap(true);

		JScrollPane scrPane = new JScrollPane(contentsTextF);
		scrPane.setPreferredSize(new Dimension(200, 120));
		scrPane.setBounds(31, 260, 250, 140);
		add(scrPane);

		updateBtn = new JButton("수정");
		updateBtn.setBounds(31, 410, 100, 20);
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bDto.getId().equals(dao.getLoginID())) {
					new updateView(seq);
				} else {
					JOptionPane.showMessageDialog(null, "수정할 권한이 없습니다.");
				}
			}
		});
		add(updateBtn);

		deleteBtn = new JButton("삭제");
		deleteBtn.addActionListener(this);
		deleteBtn.setBounds(150, 410, 100, 20);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bDto.getId().equals(dao.getLoginID())) {
					bDao.delBBS(seq);
					JOptionPane.showMessageDialog(null, "삭제 완료");
					dispose();
					new bbsListView();
					
				} else {
					JOptionPane.showMessageDialog(null, "삭제할 권한이 없습니다.");
				}
			}
		});
		add(deleteBtn);
		

		setBounds(100, 100, 320, 480);
		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
