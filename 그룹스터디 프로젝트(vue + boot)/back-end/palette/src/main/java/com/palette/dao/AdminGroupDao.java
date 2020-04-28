package com.palette.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.palette.model.GroupDto;
import com.palette.model.MemberDto;



@Mapper
@Repository
public interface AdminGroupDao {
	
	public List<GroupDto> selfOutputTest();
	
	public boolean acceptCreateGroup(GroupDto dto);

	public List<GroupDto> getAllStudyGroup();
	
	
	


} //end of AdminGroup interface
