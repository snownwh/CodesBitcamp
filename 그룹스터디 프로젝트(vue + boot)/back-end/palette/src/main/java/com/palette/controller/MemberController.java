package com.palette.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.PastOrPresent;

import com.palette.model.MemberDto;
import com.palette.model.TodoListDto;
import com.palette.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MemberController
 */
@CrossOrigin(origins = "*")
@RestController
public class MemberController {

    @Autowired
    MemberService memberService;


    @GetMapping(value = "/register") 
    public String member(){
        System.out.println("register()");
        List<MemberDto> list = memberService.selectAll();
        System.out.println(list.size());
        return "";
    }
    //로그인 session저장용 컨트롤러
    @PostMapping(value= "/getOneMember")
    public MemberDto getOneMember(MemberDto dto) {
    	System.out.println("getOneMember() 실행"+ dto.toString());
    	MemberDto member = memberService.getOneMember(dto);
    	return member;
    }
    
   //회원가입 컨트롤러
    @PostMapping(value="/createMember")
    public boolean createMember(MemberDto dto, HttpServletRequest request)throws Exception {
    	System.out.println("createMember" + dto.toString());
    	boolean check = memberService.createMember(dto);
    	System.out.println(" 추가 성공: " + check);
    	return check;
    }
    //회원가입시 아이디체크 컨트롤러
    @PostMapping(value="/checkid")
    public boolean checkId(String memberId) {
    	System.out.println("checkid"  + memberId);
    	int is = memberService.checkid(memberId);
    	return is > 0? false: true;
    	
    }
    
    //회원가입후 관심분야 선택하는 컨트롤러
    @PostMapping(value="/intersting")
    public String intersting(String interSmallSeqs, MemberDto dto) {
    	System.out.println("interesting!!!"+interSmallSeqs + dto.getInterArea());
    	int memberSeq = memberService.getSeq();
    	dto.setMemberSeq(memberSeq);
    	memberService.addInterArea(dto);
    	memberService.InterstingInsert(interSmallSeqs, memberSeq);
    	return "perfect";
    }
    
    //마이페이지 관심정보갖고 오는 컨트롤러
    @PostMapping(value= "/myPageInter")
    public MemberDto myPageInter(int memberSeq) {
    	System.out.println("myPageInter() 실행=="+ memberSeq);
    	MemberDto member = memberService.getDetailMember(memberSeq);
    	return member;
    }
    
    @PostMapping(value="/myPageInfomation")
    public MemberDto myPageInfomation(MemberDto dto) {
    	System.out.println("myPageInfomation "+ dto.getMemberSeq());
    	MemberDto member = memberService.getMyInfo(dto);
    	return member;
    }
    
    //마이페이지 내 정보 가져오는 컨트롤러
    @PostMapping(value="/updateInfo")
    public boolean updateInfo(MemberDto dto) {
    	System.out.println("udpateinfo"+ dto.toString());
    	boolean check = memberService.updateInfo(dto);
    	
    	return check;
    }
    
    //마이페이지 내 주소 변경
    @PostMapping(value="/updateAddr")
    public boolean updateAddr(MemberDto dto) {
    	System.out.println("updateADdre"+ dto.getMemberSeq()+ dto.getAddress());
    	boolean check = memberService.updateAddr(dto);
    	return check;
    }
    
    //마이페이지 내 비밀번호확인
    @PostMapping(value="/checkPass")
    public boolean checkPass(MemberDto dto) {
    	System.out.println("checkPass" + dto.getMemberSeq()+ dto.getPwd());
    	int check = memberService.checkPass(dto);
    	return check >0? true: false;
    }
    
    //마이페이지 내 비밀번호변경
    @PostMapping(value="/updatePass")
    public boolean updatePass(MemberDto dto) {
    	System.out.println("updatePass" + dto.getMemberSeq()+ dto.getPwd());
    	boolean pass = memberService.updatePass(dto);
    	return pass;
    }
    
    //마이페이지 내 관심지역 변경
    @PostMapping(value="/updateInterArea")
    public void updateInterArea(MemberDto dto) {
    	System.out.println("updateInterArea"+ dto.getMemberSeq()+ dto.getInterArea());
    	memberService.addInterArea(dto);
  
    }
    
    //마이펭지 관심분야 변경
    @PostMapping(value="/InterestingUpdate")
    public String InterestingUpdate(String interSmallSeqs, MemberDto dto) {
    	System.out.println("InterestingUpdate!!!"+interSmallSeqs + dto.getMemberSeq());
    	memberService.InterestingUpdate(interSmallSeqs, dto.getMemberSeq());
    	return "perfect";
    }
    
    //mypage회원탈퇴
    @PostMapping(value="/delUser")
    public boolean delUser(MemberDto dto) {
    	System.out.println("delUser"+ dto.getMemberId()+ "<< 아이디 시퀀스>>"+dto.getMemberSeq());
    	boolean del = memberService.delUser(dto);
    	return del;
    }
    
    
    
    //개인스터디 todo리스트 추가
    @PostMapping(value="/addTodoList")
    public boolean addTodoList(TodoListDto dto) {
    	System.out.println("addTodoList"+ dto.getMemberSeq()+ dto.getTitle());
    	boolean add = memberService.addTodoList(dto);
    	return add;
    }
    
    //개인스터디 todo리스트 전체출력
    @PostMapping(value="/selectTodoList")
    public List<TodoListDto> selectTodoList(TodoListDto dto) {
    	System.out.println("selectTodoLsit"+ dto.getMemberSeq());
    	List<TodoListDto> todo = memberService.selectTodoList(dto);
    	return todo;
    }
    
    //개인스터디 todo리스트 하나만 출력
    @PostMapping(value="/selectOneList")
    public TodoListDto selectOneList(TodoListDto dto) {
    	System.out.println("selectOneList"+ dto.getMemberSeq()+ dto.getTitle());
    	TodoListDto todo = memberService.selectOneList(dto);
    	System.out.println("++++++++++++++"+todo.toString());
    	return todo;
    }
    
    //개인스터디 todo리스트 삭제
    @PostMapping(value="/todoDel")
    public boolean todoDel(TodoListDto dto) {
    	System.out.println("delet:====>"+ dto.getTodoSeq()+dto.getMemberSeq());
    	boolean del = memberService.todoDel(dto);
    	return del;
    }
    
    //개인스터디 todo리스트 완료
    @PostMapping(value="/todoDone")
    public boolean todoDone(TodoListDto dto) {
    	System.out.println("Done=----->:"+ dto.getMemberSeq()+ dto.getDel());
    	boolean done = memberService.todoDone(dto);
    	return done;
    }
    
    //개인스터디 todo리스트 수정용
    @PostMapping(value="/todoEdit")
    public boolean todoEdit(TodoListDto dto) {
    	System.out.println("----Edit---"+ dto.getTitle()+ dto.getMemberSeq()+ dto.getTodoSeq());
    	boolean edit = memberService.todoEdit(dto);
    	return edit;
    }
    
    //todolist 완료된 리스트 뿌리기
    @PostMapping(value="/selectDoneTodoList")
    public List<TodoListDto> selectDoneTodoList(TodoListDto dto) {
    	System.out.println("selectDoneTodoList"+ dto.getMemberSeq());
    	List<TodoListDto> todo = memberService.selectDoneTodoList(dto);
    	return todo;
    }
    
    //todolist 미완료된 리스트 
    @PostMapping(value="/TodoListDoing")
    public List<TodoListDto> TodoListDoing(TodoListDto dto) {
    	System.out.println("selectDoneTodoList"+ dto.getMemberSeq());
    	List<TodoListDto> todo = memberService.TodoListDoing(dto);
    	return todo;
    }
    
    //todolist log 날짜데이터 뽑기
    @PostMapping(value="/selectDate")
    public List<TodoListDto> selectDate(TodoListDto dto) {
    	System.out.println("selectDate"+ dto.getMemberSeq());
    	List<TodoListDto> todo = memberService.selectDate(dto);
   
    	return todo;
    }
}