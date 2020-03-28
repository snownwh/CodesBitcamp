package movie.com.a.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import movie.com.a.dao.MemberDao;
import movie.com.a.model.MemberDto;


@Repository // == 저장소
public class MemberDaoImpl implements MemberDao {
	
	// DB와 통신
	@Autowired // <- 객체 생성(의존성) // applicationContext에서 설정한 DB와 연결 bean의 아이디와 변수명이 일치해야 한다.
	SqlSession sqlSession;
	
	
	
	String namespace = "Member."; // 아이디값을 연결하는 역할

	@Override
	public List<MemberDto> allMember() {
		List<MemberDto> list = sqlSession.selectList(namespace + "allMember");
		return list;
	}

	@Override
	public boolean idCheck(String id) {
		int idCheck = sqlSession.selectOne(namespace + "memberIdCheck", id);
		System.out.println(idCheck);
		if(idCheck>0) {
			return true;	
		} else {
			return false;
		}
		
		
	}

	@Override
	public boolean join(MemberDto mem){
		try {
			int memJoin = sqlSession.insert(namespace + "memberJoin", mem);
			System.out.println(memJoin);
			
			if(memJoin>0) {
				return true;	
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public MemberDto login(MemberDto mem) {
		try {
			MemberDto memLogin = sqlSession.selectOne(namespace + "memberLogin", mem);
			System.out.println(memLogin.toString());
			if(memLogin != null) {
				return memLogin;
			} else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}	
	}	
//		try {
//			List<MemberDto> memLogin = sqlSession.selectList(namespace + "memberLogin", mem);
//			System.out.println(memLogin.get(0).toString());		
//			if(memLogin != null) {
//				return memLogin;
//			} else {
//				return null;
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}	
	
	
	
	

	
	
	
	
	
}
