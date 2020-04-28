package com.palette.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palette.dao.AnonymousBoardDao;
import com.palette.dao.EmploymentDao;
import com.palette.model.AnonymousBoardDto;
import com.palette.model.BoardParams;

@Service
@Transactional
public class AnonymousBoardService {

	@Autowired
    AnonymousBoardDao anonymousBoardtDao;
	
	public ArrayList<AnonymousBoardDto> getAnonymousBoardPagingList(BoardParams boardParams) {
		ArrayList<AnonymousBoardDto> list = anonymousBoardtDao.getAnonymousBoardPagingList(boardParams);
		return list;
	}
	
	public int anonymousBoardList(BoardParams boardParams) {
		return anonymousBoardtDao.anonymousBoardList(boardParams);
	}
	
}
