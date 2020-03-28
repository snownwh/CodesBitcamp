package service;

import dto.MemberDto;

public interface MemberService {
	public boolean idCheck(String id);
	
	public boolean addMember(MemberDto dto);
	
	public MemberDto login(String id, String pswd);
}
