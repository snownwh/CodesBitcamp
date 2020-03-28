package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BbsDto;
import dto.MemberDto;

public class BbsDao {
	private static BbsDao dao = new BbsDao();
	
	public BbsDao() {
	}
	
	public static BbsDao getInstance() {
		return dao;
	}
	
	public boolean addBbs(String id, String title, String content) {
		String sql = " INSERT INTO BBS3 "
					+ "	VALUES(SEQ_BBS3.NEXTVAL, ?, "
					+ "	(SELECT NVL(MAX(REF), 0) + 1 FROM BBS), "
					+ " 0, 0, ?, ?, SYSDATE, 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 addBbs success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, title);
			psmt.setString(3, content);
			System.out.println("2/6 addBbs success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 addBbs success");
			
		} catch (SQLException e) {
			System.out.println("addBbs fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false; 
	}
	
	public List<BbsDto> getBbsList(){
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT "
				   + " FROM BBS3 "
				   + " ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null; 
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BbsDto dto = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsList success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getBbsList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getBbsList success");
			
			while(rs.next()) {
				int i = 1;
				dto = new BbsDto( rs.getInt(i++),
								  rs.getString(i++),
								  rs.getInt(i++),
								  rs.getInt(i++),
						    	  rs.getInt(i++),
								  rs.getString(i++),
								  rs.getString(i++),
								  rs.getString(i++),
								  rs.getInt(i++),
								  rs.getInt(i++));
				list.add(dto);
			}
			System.out.println("4/6 getBbsList success");
			
			
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
			e.printStackTrace();
		}
		return list;
	}
	
	public BbsDto bbsSelectOne(int seq) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, WDATE, DEL, READCOUNT " 
				+    "	FROM BBS3 "
				+ 	 "  WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BbsDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 bbsSelectOne success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 bbsSelectOne success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 bbsSelectOne success");
			
			while(rs.next()) {
				int i = 1;
				dto = new BbsDto( rs.getInt(i++),
								  rs.getString(i++),
								  rs.getInt(i++),
								  rs.getInt(i++),
						    	  rs.getInt(i++),
								  rs.getString(i++),
								  rs.getString(i++),
								  rs.getString(i++),
								  rs.getInt(i++),
								  rs.getInt(i++));
				
			}
			System.out.println("4/6 bbsSelectOne success");
			
		} catch (SQLException e) {
			System.out.println("bbsSelectOne fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
	
	public boolean bbsUpdate(int seq, String title, String content) {
		String sql = " UPDATE BBS3"
				+ 	 " SET TITLE = ?, CONTENT = ? "
				+ 	 " WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		System.out.println("다오업데이트 seq:" + seq);
		System.out.println("다오업데이트 title:" + title);
		System.out.println("다오업데이트 content:" + content);
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 bbsUpdate success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			System.out.println("2/6 bbsUpdate success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 bbsUpdate success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0?true:false;
		
	}
	
	public boolean bbsDelete(int seq) {
		String sql = " UPDATE BBS3  "
				+ 	 " SET DEL = 1 "
				+ 	 " WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 bbsDelete success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 bbsDelete success");
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("bbsDelete fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0?true:false;
		
	}
	
	public boolean bbsAnswer(int seq, BbsDto dto) {
		// update
		String sql1 = " UPDATE BBS3 "
				+ 	 " SET STEP = STEP + 1 "
				+ 	 " WHERE REF = ( SELECT REF FROM BBS3 WHERE SEQ = ? )"
				+ "		 AND STEP > ( SELECT STEP FROM BBS3 WHERE SEQ = ? ) ";
		
		String sql2 = " INSERT INTO BBS3 "
				+ 	  " VALUES(SEQ_BBS3.NEXTVAL, ?, "
				+ 	  "	( SELECT REF FROM BBS3 WHERE SEQ = ?), "
				+ 	  " ( SELECT STEP FROM BBS3 WHERE SEQ = ?) + 1,"
				+ 	  " ( SELECT DEPTH FROM BBS3 WHERE SEQ = ?) + 1,"
				+ 	  " ?, ?, SYSDATE, 0, 0) ";
				
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 bbsAnswer success");
			
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			System.out.println("2/6 bbsAnswer success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 bbsAnswer success");
			
			psmt.clearParameters();
			System.out.println("4/6 bbsAnswer success");
			
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, dto.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, dto.getTitle());
			psmt.setString(6, dto.getContent());
			System.out.println("5/6 bbsAnswer success");
			
			count = psmt.executeUpdate();
			System.out.println("6/6 bbsAnswer success");
			
		} catch (SQLException e) {
			System.out.println("bbsAnswer fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		return count>0?true:false;
		
		
		
		
	}
	
}
