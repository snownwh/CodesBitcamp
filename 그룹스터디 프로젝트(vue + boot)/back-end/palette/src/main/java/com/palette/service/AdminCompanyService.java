package com.palette.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palette.dao.adminCompanyDao;
import com.palette.model.MemberDto;


@Service
@Transactional
public class AdminCompanyService {
	@Autowired
	adminCompanyDao adminmemberdao;
	
	public List<MemberDto> adminGetCompanyMemberListAll(){
		return adminmemberdao.adminGetCompanyMemberListAll();
	}
	
	public MemberDto adminGetCompanyInfoOne(MemberDto dto) {
		
		return adminmemberdao.adminGetCompanyInfoOne(dto);
	}
	
	public MemberDto approvalrequestCompanyList(){
		return adminmemberdao.approvalrequestCompanyList();
	}
	
	public MemberDto finishApprovalCompanyList() {
		return adminmemberdao.finishApprovalCompanyList();
	}
	
	public List<String> adminGetCompanyLocationALl(){
		return adminmemberdao.adminGetCompanyLocationALl();
		
	}
	
	public List<String> adminGetCompanyNmaeAll(){
		return adminmemberdao.adminGetCompanyNmaeAll();
	}

	
	public boolean changeAUTHforCompanyMember(MemberDto dto){
		return adminmemberdao.changeAUTHforCompanyMember(dto);
	}

	
	
	
	
	

}// end of adminCompanyService class
