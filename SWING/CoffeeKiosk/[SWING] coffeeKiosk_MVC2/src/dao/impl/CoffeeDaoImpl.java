package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CoffeeDao;
import db.DBClose;
import db.DBConnection;
import dto.CoffeeDto;

public class CoffeeDaoImpl implements CoffeeDao{

	@Override
	public List<CoffeeDto> getCoffeeList() {
		String sql = " SELECT COFFEE_NAME, C_SHORT, C_TALL, C_GRANDE "
				+ "	FROM COFFEE ";
		
		System.out.println("sql: "+sql);
		
		Connection conn = null; // DB Connection
		PreparedStatement psmt = null; // SQL
		ResultSet rs = null; // result
		
		List<CoffeeDto> list = new ArrayList<CoffeeDto>();
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CoffeeDto dto = new CoffeeDto(rs.getString(1), // 커피 이름	
											  rs.getInt(2),
											  rs.getInt(3),
											  rs.getInt(4)
						);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return list;
	}
	
}
