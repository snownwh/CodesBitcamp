package KSJ.files.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DB.DBClose;
import DB.DBConnection;
import KSJ.exhibit.dto.ExhibitDto;
import KSJ.files.dto.FilesDto;

public class FilesDao {
	
	//Singleton
	private static FilesDao dao = null;
	
	private FilesDao() {
		DBConnection.initConnection();
	}
	
	public static FilesDao getInstance() {
		if(dao == null) dao = new FilesDao();
		return dao;
	}
	
	
	
	// 전시 seq 꺼내기
	public int getExhibitSeq(String certi_num) {
		String sql=   " SELECT SEQ "
					+ " FROM (SELECT ROW_NUMBER()OVER( ORDER BY SEQ DESC)  AS RNUM, SEQ, TITLE, FILENAME "
							+ " FROM EXHIBIT "
							+ " WHERE CERTI_NUM = ? )"
					+ " WHERE RNUM = 1 ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int exhibit_seq= -1;
		
		ExhibitDto dto = new ExhibitDto();
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, certi_num);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				exhibit_seq = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBClose.close(psmt, conn, rs);
		}
		return exhibit_seq;
	}
	
	// insert file 
	public boolean insertFile(FilesDto dto) {
		
		String sql = " INSERT INTO FILES ( SEQ, FILENAME, ORIGIN_NAME, FILEPATH, BBS_NAME, BBS_SEQ, DEL, FILE_SEQ ) "
				+ " VALUES ( SEQ_FILES.NEXTVAL, ?, ?, ?, ?, ?, 0 , ? ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getFilename());
			psmt.setString(2, dto.getOriginName());
			psmt.setString(3, dto.getFilepath());
			psmt.setString(4, dto.getBbsName());
			psmt.setInt(5, dto.getBbsSeq());
			psmt.setInt(6, dto.getFile_seq());
			
			count = psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count >0? true:false;
		
	}
	
	// 	타이틀 이미지 Dto 빼기 
	public FilesDto getTitleImg(int bbs_seq, int file_seq) {
		String sql =  " SELECT SEQ, FILENAME, ORIGIN_NAME, FILEPATH, BBS_NAME, BBS_SEQ, DEL, FILE_SEQ "
					+ " FROM FILES "
					+ " WHERE BBS_NAME = 'exhibit' AND BBS_SEQ = ? AND FILE_SEQ = ? ";
	
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		FilesDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bbs_seq);
			psmt.setInt(2, file_seq);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				int i =1;
				dto = new FilesDto( rs.getInt(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getInt(i++), 
									rs.getInt(i++), 
									rs.getInt(i++));
				
				
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return dto;
		
	
	}
	
	// 전시 삭제하기
		public boolean deleteExhibitFile(String bbs_name ,int bbs_seq) {
			String sql=   " UPDATE FILES "
						+ " SET DEL = 1 "
						+ " WHERE BBS_NAME = ? AND BBS_SEQ = " + bbs_seq;
			Connection conn = null;
			PreparedStatement psmt = null;

			int count[] = new int[100];
			boolean result = true;
			
			try {

				conn = DBConnection.getConnection();
				
				//commit 
				conn.setAutoCommit(false);
				
				psmt = conn.prepareStatement(sql);
				
				System.out.println(" psmt.getUpdateCount():"+ psmt.getUpdateCount());
				for (int i = 0; i < 100; i++) {
					
					psmt.setString(1, bbs_name);
					psmt.addBatch();
				}

				count = psmt.executeBatch();
				
				for (int i = 0; i < count.length; i++) {
					if(count[i]!=-2) {
						result = false;
						break;
					}
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					conn.rollback();	// 오류시 롤백 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			finally {
				DBClose.close(psmt, conn, null);
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return result;
			
			
		}
	
	
		// content file 업로드
		public boolean insertContentFile(List<FilesDto> flist) {
			String sql =  " INSERT INTO FILES ( SEQ, FILENAME, ORIGIN_NAME, FILEPATH, BBS_NAME, BBS_SEQ, DEL, FILE_SEQ )"
						+ " VALUES ( SEQ_FILES.NEXTVAL, ?, ?, ?, ?, ?, 0, ? ) ";
			
			Connection conn = null;
			PreparedStatement psmt = null;

			int count[] = new int[flist.size()];
			boolean result = true;
			
			try {

				conn = DBConnection.getConnection();
				
				//commit 
				conn.setAutoCommit(false);
				
				psmt = conn.prepareStatement(sql);
				
				System.out.println(" psmt.getUpdateCount():"+ psmt.getUpdateCount());
				for (int i = 0; i < count.length; i++) {
					FilesDto dto = flist.get(i);
					
					psmt.setString(1, dto.getFilename() );
					psmt.setString(2, dto.getOriginName());
					psmt.setString(3, dto.getFilepath());
					psmt.setString(4, dto.getBbsName());
					psmt.setInt(5, dto.getBbsSeq());
					psmt.setInt(6, dto.getFile_seq());
					psmt.addBatch();
				}

				count = psmt.executeBatch();
				
				for (int i = 0; i < count.length; i++) {
					if(count[i]!=-2) {
						result = false;
						break;
					}
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					conn.rollback();	// 오류시 롤백 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			finally {
				try {
					conn.commit();
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DBClose.close(psmt, conn, null);
			}
			
			return result;
			
		}
		
	
	
}
