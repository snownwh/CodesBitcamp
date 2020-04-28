package com.palette.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palette.dao.AdminGroupDao;
import com.palette.model.GroupDto;

@Service
@Transactional
public class AdminGroupService {
	@Autowired
	AdminGroupDao admingroupdao;
	
	
	public List<GroupDto> selfOutputTest(){
		return admingroupdao.selfOutputTest();
	}
	
	public boolean acceptCreateGroup(GroupDto dto){
		return admingroupdao.acceptCreateGroup(dto);
	}

	public List<GroupDto> getAllStudyGroup(){
		return admingroupdao.getAllStudyGroup();
	}
	
	

}//end of adminMemberGroup class
