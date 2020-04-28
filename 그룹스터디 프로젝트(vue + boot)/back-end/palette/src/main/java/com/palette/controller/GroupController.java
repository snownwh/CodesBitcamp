package com.palette.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.palette.model.BoardParams;
import com.palette.model.CommentDto;
import com.palette.model.GroupBoardDto;
import com.palette.model.GroupDto;
import com.palette.model.GroupMemberDto;
import com.palette.model.GroupParams;
import com.palette.model.GroupSchedule;
import com.palette.model.InterBigDto;
import com.palette.s3.ReferenceVo;
import com.palette.s3.S3Uploader;
import com.palette.service.GroupService;


@CrossOrigin(origins = "*")
@RestController
public class GroupController {

    @Autowired
    S3Uploader s3Uploader;

    @Autowired
    GroupService groupService;

    // not-login
    @GetMapping(value = "/getAllGroup")
    public List<GroupDto> getAllGroup() {
    	System.out.println("getAllGroup 메소드 실행");
    	List<GroupDto> list = groupService.getAllGroup();
    	return list;
    }
    // login
    @PostMapping(value="/getMyGroup")
    public ArrayList<GroupDto> getMyGroup(GroupParams groupParams){
    	System.out.println("getMyGroup() 실행");
    	 ArrayList<GroupDto> list = groupService.getMyGroup(groupParams);
    	 return list;
    }
    @PostMapping(value="/getMyOtherGroup")
    public ArrayList<GroupDto> getMyOtherGroup(GroupParams groupParams){
    	System.out.println("getMyOtherGroup() 실행");

    	 ArrayList<GroupDto> list = groupService.getMyOtherGroup(groupParams);
    	 return list;
    }
    
    @PostMapping(value="/groupSearchList")
    public ArrayList<GroupDto> groupSearchList(GroupParams groupParams){
        ArrayList<GroupDto> list = null;
        if(groupParams.getInterBigSeq() == 0){
            list = (ArrayList)groupService.getAllGroup();
        }else{
            list = groupService.groupSearchList(groupParams);
        }
    	
    	return list;
    }
    
    @PostMapping(value = "/getOneGroup")
    public GroupDto getOneGroup(GroupDto insertDto){
        System.out.println("getOneGroup()");
        GroupDto outDto = groupService.getOneGroup(insertDto);
    	return outDto;
    }
    
    
    @PostMapping(value = "/creatGroupApply")
    public String creatGroupApply(GroupDto groupDto, GroupSchedule groupSchedule, MultipartFile file)
            throws IOException {
        System.out.println("creatGroupApply");
        groupDto.setInterBigSeq(groupService.searchInterBigSeq(groupDto.getInterSmallSeq()));
        if(!file.isEmpty()){
            String imagePath = s3Uploader.upload(file, "groupImage");
            groupDto.setImage(imagePath);
        }else{
            groupDto.setImage("");
        }
        groupService.createGroup(groupDto);
        groupSchedule.setGroupInfoSeq(groupService.currGroupInfoSeq());
        groupService.addSchedule(groupSchedule);
        return "";
    }


    
    
    // Board
    @PostMapping(value="/groupPagingList")
    public ArrayList<GroupBoardDto> getGroupPagingList(BoardParams boardParams) {
    	System.out.println("getGroupPagingList() : "+boardParams.toString());
    	boardParams.setStart( (boardParams.getPage()-1)*boardParams.getLimit() );
    	ArrayList<GroupBoardDto> list =	groupService.getGroupPagingList(boardParams);
    	System.out.println("list.size= "+list.size());
    	return list;
    }
    
    @PostMapping(value="/groupBoardTotal")
    public String getGroupBoardTotal(BoardParams boardParams) {
    	System.out.println("getGroupBoardTotal()" + boardParams.toString());
        int total = groupService.getGroupBoardTotal(boardParams);
        String stotal = total +"";
    	System.out.println("total: "+total);
    	return stotal;
    }
    
    @PostMapping(value="/groupBoardDetail")
    public GroupBoardDto getGroupBoardDetail(int boardSeq) {
        System.out.println("getGroupBoardDetail" + boardSeq);
        groupService.updateReadCount(boardSeq);
        return groupService.getGroupBoardDetail(boardSeq);
    }

    @PostMapping(value = "/groupBoardDelete")
    public String groupBoardDelete(int boardSeq){
        System.out.println("groupBoardDelete()");
        groupService.groupBoardDelete(boardSeq);
        return "";
    }

    @GetMapping(value = "/insertGroupBoard")
    public String insertGroupBoard(GroupBoardDto groupBoardDto){
        System.out.println("insertGroupBoard()");
        groupService.insertGroupBoard(groupBoardDto);
        return "";
    }

    @GetMapping(value = "/updateGroupBoard")
    public String updateGroupBoard(GroupBoardDto groupBoardDto){
        System.out.println("updateGroupBoard()" + groupBoardDto.toString());
        groupService.updateGroupBoard(groupBoardDto);
        return "";
    }
    

    
    // Pds
    @PostMapping(value="/groupPdsList")
    public ArrayList<GroupBoardDto> getgroupPdsList(int groupSeq) {
    	System.out.println("getgroupPdsList");
        ArrayList<GroupBoardDto> list =	groupService.getgroupPdsList(groupSeq);
        return list;
    }
    
  
    @GetMapping(value="/getGroupMemberName")
    public List<GroupMemberDto> getGroupMemberName(int interGroupSeq){
    	System.out.println("getGroupMemberName()");
    	List<GroupMemberDto> list = groupService.getGroupMemberName(interGroupSeq);
    	return list;
    }
    
    @GetMapping(value="/getInterListAll")
    public List<InterBigDto> getInterListAll() {
        System.out.println("getInterListAll()");
        List<InterBigDto> list = groupService.getInterListAll();
        return list;
    }

    @PostMapping(value = "/insertGroupReference")
    public String insertGroupReference(ReferenceVo form) throws IOException{
        groupService.insertBoardReference(form);
        return "";
    }
    
    // Comment
    @PostMapping(value="/groupBoardDetailComments")
    public ArrayList<CommentDto> getGroupBoardDetailComments(int boardSeq) {
        System.out.println("getGroupBoardDetailComments() : " + boardSeq);
        ArrayList<CommentDto> list = groupService.groupBoardDetailComments(boardSeq);
        return list;
    }

    // attendance
    @PostMapping(value="/attendGroup")
    public String getAttendGroup(String checkday, GroupSchedule groupSchedule) {
    	System.out.println("getAttendGroup() " +checkday +"/" + groupSchedule.toString());
    	
    	int result = groupService.getAttendGroup(checkday, groupSchedule);

    	String s_result= result+"";
    	    	
    	return s_result;
    }
	@PostMapping(value="/getAttendStatus")
	public String checkAttend(GroupSchedule groupSchedule) {
		System.out.println("getAttendStatus()");
		
		int result = groupService.checkAttend(groupSchedule);
		
		String s_result = result+"";
		
		return s_result;
		
	}
}
