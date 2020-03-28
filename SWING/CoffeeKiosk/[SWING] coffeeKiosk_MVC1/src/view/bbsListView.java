package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.BbsDto;
import javabean.BbsDao;

public class bbsListView extends JFrame implements ActionListener, MouseListener {

	private JTable jtable;
	private JScrollPane jscrPane;
	private JTextField selectField;

	private JButton writeBtn;
	private JButton selectBtn;
	
	private JComboBox<String> choiceList;
	private JTextField selectFields;
	private JButton selectBtns;

	String columnNames[] = { "번호", "제목", "작성자" };

	Object rowData[][];

	DefaultTableModel model; // table의 넓이를 설정

	List<BbsDto> list = null;

	public bbsListView() {
		super("게시판");

		setLayout(null);

		JLabel label = new JLabel("게시판");
		label.setBounds(10, 10, 120, 15);
		add(label);

		// dao를 통해서 list를 취득
		BbsDao dao = BbsDao.getInstance();
		list = dao.getBbsList();

		// jtable row를 생성
		rowData = new Object[list.size()][3];

		// list에서 테이블로 데이터를 삽입하기 위한 처리
		for (int i = 0; i < list.size(); i++) {
			BbsDto dto = list.get(i);

			rowData[i][0] = i + 1; // 글의 번호
			rowData[i][1] = dto.getTitle(); // 글의 제목
			rowData[i][2] = dto.getId(); // 작성자
		}

		// 테이블 관련
		// 테이블 폭을 설정하기 위한 Model
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);

		// 테이블 생성
		jtable = new JTable(model);
		jtable.addMouseListener(this);

		// column의 폭을 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(50); // 번호
		jtable.getColumnModel().getColumn(1).setMaxWidth(500); // 제목
		jtable.getColumnModel().getColumn(2).setMaxWidth(200); // 작성자

		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(10, 50, 600, 300);
		add(jscrPane);

		writeBtn = new JButton("글쓰기");
		writeBtn.setBounds(510, 10, 100, 20);
		writeBtn.addActionListener(this);
		add(writeBtn);

		setBackground(new Color(0, 0, 128));
		setBounds(100, 100, 640, 480);
		setVisible(true);

		//검색
		String selects[] = new String[] {"선택", "제목", "내용", "작성자"};
		choiceList = new JComboBox<String>(selects);
		choiceList.setBounds(330, 360, 60, 20);
		add(choiceList);

		selectFields = new JTextField();
		selectFields.setBounds(400, 360, 120, 20);
		add(selectFields);

		selectBtns = new JButton("검색");
		selectBtns.setBounds(530, 360, 80, 20);
		selectBtns.addActionListener(this);
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if (choiceList.getSelectedIndex() == 0) {
//					list = dao.titleSearchBBS(selectField.getText());
//					System.out.println(choiceList.getSelectedIndex());
//					System.out.println(selectField.getText());
//					
//					rowData = new Object[list.size()][3];
//					
//					// list에서 테이블로 데이터를 삽입하기 위한 처리
//					for (int i = 0; i < list.size(); i++) {
//						BbsDto dto = list.get(i);
//						
//						rowData[i][0] = i + 1; // 글의 번호
//						rowData[i][1] = dto.getTitle(); // 글의 제목
//						rowData[i][2] = dto.getId(); // 작성자
//					}
//				} else if (choiceList.getSelectedIndex() == 1) {
//					dao.contentSearchBBS(selectField.getText());
//				} else if (choiceList.getSelectedIndex() == 2) {
//					dao.writerSearchBBS(selectField.getText());
//				}
//				
//			}
//		}); 
			
			
		add(selectBtns);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton)e.getSource();
		
		if(btn == selectBtns) {
			String selectedItem = (String)choiceList.getSelectedItem();
//			JOptionPane.showMessageDialog(null, selectedItem);
			
			// DB에 접근 -> 검색된 글들
			BbsDao dao = BbsDao.getInstance();
			list = dao.getSelectList(selectedItem, selectFields.getText());
			
			//빈칸 확인
			if(list.size() == 0 || selectFields.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "검색한 단어로 데이터를 찾지 못하였습니다.");
				// 원본의 list를 호출
				list = dao.getBbsList();
			} 
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).toString());
			}
			
			// jtable row를 생성
			rowData = new Object[list.size()][3];

			// list에서 테이블로 데이터를 삽입하기 위한 처리
			for (int i = 0; i < list.size(); i++) {
				BbsDto dto = list.get(i);

				rowData[i][0] = i + 1; // 글의 번호
				rowData[i][1] = dto.getTitle(); // 글의 제목
				rowData[i][2] = dto.getId(); // 작성자
			}

			// 테이블 관련
			// 테이블 폭을 설정하기 위한 Model
			model = new DefaultTableModel(columnNames, 0);
			model.setDataVector(rowData, columnNames);
			
			jtable.setModel(model);
			
			// column의 폭을 설정
			jtable.getColumnModel().getColumn(0).setMaxWidth(50); // 번호
			jtable.getColumnModel().getColumn(1).setMaxWidth(500); // 제목
			jtable.getColumnModel().getColumn(2).setMaxWidth(200); // 작성자
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = jtable.getSelectedRow();
		BbsDto dto = list.get(row);
		BbsDao bDao = BbsDao.getInstance();
		boolean b = bDao.selectBBS(dto);
		if (b) {
			new bbsDetail(dto.getSeq());
			this.dispose();
		}
		System.out.println(dto.toString());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
