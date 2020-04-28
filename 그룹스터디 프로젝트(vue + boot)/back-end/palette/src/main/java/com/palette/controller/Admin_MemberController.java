package com.palette.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.palette.model.MemberDto;
import com.palette.service.AdminMemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*")
@RestController
public class Admin_MemberController {
   @Autowired
   AdminMemberService AdminMemberService;
   
   
/*
   for login function in the administrator
   */
@PostMapping(value = "/adminLoginCheck")
public MemberDto loginCheck(MemberDto memdto){
   //  System.out.println("loginCheck()");
   //  System.out.println(" receive data = " + memdto );
    MemberDto member = AdminMemberService.idpwcheck(memdto);
   //  System.out.println("logincheck result " + member );
    return member;
   }

@PostMapping(value = "/AdminMemberSelectAll")
public List<MemberDto> showGeneralMemberAll(){
       System.out.println("showGeneralMemberAll()");
      List<MemberDto> memberList = AdminMemberService.memberSelectAll();
       System.out.println("logincheck result " + memberList );
       return memberList;
      }

      

      
      

      //
      //    
      //    @GetMapping(value = "/adm_register")
      //    public String member(){
      //        System.out.println("register()");
      //        List<MemberDto> list = AdminMemberService.selectAll();
      //        System.out.println(list.size());
      //        return "";
      //    }
      //    
      //    @PostMapping(value= "/adm_getOneMember")
      //    public MemberDto getOneMember(MemberDto dto, HttpServletRequest request)throws Exception {
      //    	System.out.println("getOneMember() 실행");
      //    	MemberDto member = AdminMemberService.getOneMember(dto);
      //    	return member;
      //    }
      
   }//end of Admin_MemberController class
