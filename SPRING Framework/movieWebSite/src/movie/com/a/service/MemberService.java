package movie.com.a.service;

import java.util.List;

import movie.com.a.model.MemberDto;

public interface MemberService {

	public List<MemberDto> allMember();
	
	public boolean idCheck(String id);
	
	public boolean join(MemberDto mem);
	
	public MemberDto login(MemberDto mem);
}
