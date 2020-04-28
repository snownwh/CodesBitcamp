package com.palette.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palette.model.AnonymousBoardDto;
import com.palette.model.BoardParams;
import com.palette.service.AnonymousBoardService;



@CrossOrigin(origins = "*")
@RestController
public class AnonymousBoardController {

	@Autowired
	AnonymousBoardService anonymousBoardService;
	
//  익명게시판 페이징
	@PostMapping(value="/anonymousBoardPagingList")
    public ArrayList<AnonymousBoardDto> getAnonymousBoardPagingList(BoardParams boardParams) {
		System.out.println("anonymousBoardPagingList() : "+ boardParams.toString());
    	boardParams.setStart( (boardParams.getPage()-1)*boardParams.getLimit() );
    	ArrayList<AnonymousBoardDto> list =	anonymousBoardService.getAnonymousBoardPagingList(boardParams);
    	for(int i = 0; i < list.size(); i++) {
    		String sFinal = list.get(i).getFinalnum()+"번째 글";
    		list.get(i).setSfinalnum(sFinal);
    		
    	}
    	return list;
    }
	
//	cv 리스트 갯수
	@PostMapping(value = "/anonymousBoardList")
    public String anonymousBoardList(BoardParams boardParams) {
		System.out.println("anonymousBoardList() 실행");
		int total =	anonymousBoardService.anonymousBoardList(boardParams);
		String stotal = total + "";
		System.out.println("total :" + total);
		return stotal;
	}
}
