package com.palette.service;

import java.util.List;

import com.palette.dao.AdminMemberDao;
import com.palette.model.MemberDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdminMemberService {
    @Autowired
    AdminMemberDao adminmemberdao;


    public MemberDto idpwcheck(MemberDto memdto){

        return adminmemberdao.idpwcheck(memdto);
    }

    public List<MemberDto> memberSelectAll(){

        return adminmemberdao.memberSelectAll();
    }


    
	

}//end of adminMemberService class
