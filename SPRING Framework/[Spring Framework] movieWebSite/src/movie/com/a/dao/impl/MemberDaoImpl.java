package movie.com.a.dao.impl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import movie.com.a.dao.MemberDao;
import movie.com.a.model.MemberDto;


@Repository
public class MemberDaoImpl implements MemberDao {
	
	// DB와 통신
	@Autowired
	SqlSession sqlSession;
	
	
	
	String namespace = "Member.";

	// 모든 회원 리스트
	@Override
	public List<MemberDto> allMember() {
		List<MemberDto> list = sqlSession.selectList(namespace + "allMember");
		return list;
	}

	// 회원가입시 아이디 중복체크
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

	// 회원가입
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

	// 로그인
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

	
	
	
	

	
	
	
	
	
}
