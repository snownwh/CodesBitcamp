package com.palette.dao;



import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.palette.model.MemberDto;

@Mapper
@Repository
public interface AdminMemberDao {

    public MemberDto idpwcheck(MemberDto memdto);
    public List<MemberDto> memberSelectAll();

    
	

}//end of adminMemberDao interface
