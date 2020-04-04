package movie.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.com.a.dao.MemberDao;
import movie.com.a.model.MemberDto;
import movie.com.a.service.MemberService;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao memberDao;

	// 모든 회원 리스트
	@Override
	public List<MemberDto> allMember() {
		return memberDao.allMember();
	}

	// 회원가입시 아이디 중복체크
	@Override
	public boolean idCheck(String id) {
		return memberDao.idCheck(id);
	}

	// 회원가입
	@Override
	public boolean join(MemberDto mem) {
		return memberDao.join(mem);
	}

	// 로그인
	@Override
	public MemberDto login(MemberDto mem) {
		return memberDao.login(mem);
	}
	
	
	
}
