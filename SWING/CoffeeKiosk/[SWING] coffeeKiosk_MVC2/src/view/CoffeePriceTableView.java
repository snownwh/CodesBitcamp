package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import dto.CoffeeDto;


public class CoffeePriceTableView extends JFrame implements ActionListener,MouseListener{
	private JTable jtable, ojtable;
	private JScrollPane jscrPane, oJscrPane;
	private JTextField selectField;


	private JComboBox<String> choiceList;
	private JTextField selectFields;
	private JButton selectBtns;
	
	String columnNames[] = { "ESPROSSO BEVERAGES", "SHORT", "TALL", "GRANDE" };
	String oColumnNames[] = { "커피명", "사이즈", "시럽", "휘핑크림", "잔", "금액" };
	
	Object rowData[][], oRowData[][];
	
	DefaultTableModel model, oModel;

	List<CoffeeDto> list = null;
	
	public CoffeePriceTableView(List<CoffeeDto> list) {
		super("커피 주문");
		setLayout(null);
		// 커피 종류 
		JLabel label = new JLabel("커피 가격표");
		label.setBounds(10, 10, 120, 15);
		add(label);

		// dao를 통해서 list를 취득
//		Singleton s = Singleton.getInstance();
		this.list = list;

		// jtable row를 생성
		rowData = new Object[list.size()][4];

		// list에서 테이블로 데이터를 삽입하기 위한 처리
		for (int i = 0; i < list.size(); i++) {
			CoffeeDto dto = list.get(i);

			rowData[i][0] = dto.getCoffeeName(); // 커피 이름
			rowData[i][1] = dto.getC_short(); // 숏
			rowData[i][2] = dto.getC_tall(); // 톨
			rowData[i][3] = dto.getC_grande(); // 그란데
		}

		// 테이블 관련
		// 테이블 폭을 설정하기 위한 Model
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);

		// 테이블 생성
		jtable = new JTable(model);
		jtable.addMouseListener(this);

		// column의 폭을 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(300); // 커피 이름
		jtable.getColumnModel().getColumn(1).setMaxWidth(100); // 숏
		jtable.getColumnModel().getColumn(2).setMaxWidth(100); // 톨
		jtable.getColumnModel().getColumn(3).setMaxWidth(100); // 그란데

		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(10, 30, 600, 180);
		add(jscrPane);
		
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(10, 30, 600, 180);
		add(jscrPane);
		
		setBounds(100, 100, 620, 250);
		setVisible(true);
		
//		// 커피 주문 내역
//		JLabel oLabel = new JLabel("주문 내역");
//		oLabel.setBounds(10, 230, 120, 15);
//		add(oLabel);
//
//		// dao를 통해서 list를 취득
////		Singleton s = Singleton.getInstance();
////		this.list = list;
//
//		// jtable row를 생성
////		oRowData = new Object[list.size()][6];
//
//		// list에서 테이블로 데이터를 삽입하기 위한 처리
////		for (int i = 0; i < list.size(); i++) {
////			CoffeeDto dto = list.get(i);
////
////			rowData[i][0] = dto.getCoffeeName(); // 커피 이름
////			rowData[i][1] = dto.getC_short(); // 숏
////			rowData[i][2] = dto.getC_tall(); // 톨
////			rowData[i][3] = dto.getC_grande(); // 그란데
////		}
//
//		// 테이블 관련
//		// 테이블 폭을 설정하기 위한 Model
//		oModel = new DefaultTableModel(oColumnNames, 0);
////		oModel.setDataVector(rowData, columnNames);
//
//		// 테이블 생성
//		ojtable = new JTable(oModel);
//		ojtable.addMouseListener(this);
//		
//		// column의 폭을 설정
//		ojtable.getColumnModel().getColumn(0).setMaxWidth(200); // 커피명
//		ojtable.getColumnModel().getColumn(1).setMaxWidth(70); // 사이즈
//		ojtable.getColumnModel().getColumn(2).setMaxWidth(70); // 시럽
//		ojtable.getColumnModel().getColumn(3).setMaxWidth(70); // 휘핑크림
//		ojtable.getColumnModel().getColumn(4).setMaxWidth(70); // 잔
//		ojtable.getColumnModel().getColumn(5).setMaxWidth(100); // 금
//		
//		oJscrPane = new JScrollPane(ojtable);
//		oJscrPane.setBounds(10, 250, 600, 180);
//		add(oJscrPane);
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
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
