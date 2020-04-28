package com.palette.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.palette.model.MemberDto;

@Mapper
@Repository
public interface adminCompanyDao {
	
	List<MemberDto> adminGetCompanyMemberListAll();
	
	MemberDto adminGetCompanyInfoOne(MemberDto dto);
	
	MemberDto approvalrequestCompanyList();
	
	MemberDto finishApprovalCompanyList();
	
	List<String> adminGetCompanyLocationALl();
	
	List<String> adminGetCompanyNmaeAll();

	public boolean changeAUTHforCompanyMember(MemberDto dto);

}
