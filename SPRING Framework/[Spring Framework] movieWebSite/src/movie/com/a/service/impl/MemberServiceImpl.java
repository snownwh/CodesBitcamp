package movie.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.com.a.dao.MemberDao;
import movie.com.a.model.MemberDto;
import movie.com.a.service.MemberService;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired // 멤버다오의 정보를 스프링이 넣어준다.(의존성)
	MemberDao memberDao;

	@Override
	public List<MemberDto> allMember() {
		return memberDao.allMember();
	}

	@Override
	public boolean idCheck(String id) {
		return memberDao.idCheck(id);
	}

	@Override
	public boolean join(MemberDto mem) {
		return memberDao.join(mem);
	}

	@Override
	public MemberDto login(MemberDto mem) {
		return memberDao.login(mem);
	}
	
	
	
}
