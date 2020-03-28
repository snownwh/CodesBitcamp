package dao;

import dto.MemberDto;

public interface MemberDao {
	public boolean idCheck(String id);
	
	public boolean addMember(MemberDto dto);
	
	public MemberDto login(String id, String pswd);
}
