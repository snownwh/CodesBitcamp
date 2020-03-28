package service.impl;

import dao.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.MemberDto;
import service.MemberService;

public class MemberServiceImpl implements MemberService{
	MemberDao memDao = new MemberDaoImpl();

	@Override
	public boolean idCheck(String id) {
		return memDao.idCheck(id);
	}

	@Override
	public boolean addMember(MemberDto dto) {
		return memDao.addMember(dto);
	}
	
	public MemberDto login(String id, String pswd) {
		return memDao.login(id, pswd);
	}
	
	
	
	
}
