package movie.com.a.service;

import java.util.List;

import movie.com.a.model.MemberDto;

public interface MemberService {

	// 모든 회원 리스트
	public List<MemberDto> allMember();
	
	// 회원가입시 아이디 중복체크
	public boolean idCheck(String id);
	
	// 회원가입
	public boolean join(MemberDto mem);
	
	// 로그인
	public MemberDto login(MemberDto mem);
}
