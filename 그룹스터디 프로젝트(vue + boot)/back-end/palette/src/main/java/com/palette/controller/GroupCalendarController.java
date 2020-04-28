package com.palette.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palette.model.CalendarDto;
import com.palette.model.GroupDto;
import com.palette.service.GroupService;

@CrossOrigin(origins = "*")
@RestController
public class GroupCalendarController {

	 @Autowired
	 GroupService groupService;
	  
	 @PostMapping(value="/getGroupCalendar")
	 public List<CalendarDto> getGroupCalendar(GroupDto groupDto) {
		System.out.println("getGroupCalendar() = "+groupDto); 	
	  	List<CalendarDto> list = groupService.getGroupCalendar(groupDto);
    	System.out.println(list.toString());
	    	
	    	return list;
	 }
	 
	 @GetMapping(value="/insertGroupCalendar")		 
	 public String insertGroupCalendar(CalendarDto calendarDto) {
		 System.out.println("insertGroupCalendar : " + calendarDto.toString());
		 groupService.insertGroupCalendar(calendarDto);
		 return "";
	 }
	 
	 @GetMapping(value="/updateGroupCalendar")
	 public String updateGroupCalendar(CalendarDto calendarDto) {
		 System.out.println("updateGroupCalendar() : "+calendarDto);
		 groupService.updateGroupCalendar(calendarDto);
		 return "";
	 }
	 
	 
	 @PostMapping(value="/deleteGroupCalendar")
	 public String deleteGroupCalendar(int seq) {
		 System.out.println("deleteGroupCalendar() : "+seq);
		 groupService.deleteGroupCalendar(seq);
		 return "";
	 }
	 
	 @PostMapping(value = "/resizeCalendar")
	 public String resizeCalendar(CalendarDto calendarDto) {
		 System.out.println("resizeCalendar() :"+calendarDto);
		 groupService.resizeCalendar(calendarDto);
		 
		 return "";
	 }
	 
	 
}
