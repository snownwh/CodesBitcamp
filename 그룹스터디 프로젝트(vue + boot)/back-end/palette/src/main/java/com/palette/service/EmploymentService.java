package com.palette.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palette.dao.EmploymentDao;
import com.palette.model.EmploymentBoardDto;

@Service
@Transactional
public class EmploymentService {
	
	@Autowired
    EmploymentDao employmentDao;

	public List<EmploymentBoardDto> getAllRecuritingInfo() {
		return employmentDao.getAllRecuritingInfo();
	}

	public EmploymentBoardDto getOneRecruit(int empBoardSeq) {
		return employmentDao.getOneRecruit(empBoardSeq);
	}
	
	public void readCount(int empBoardSeq) {
		employmentDao.readCount(empBoardSeq);
	}
	
	public boolean insertRecruit(EmploymentBoardDto dto) {
		int len = employmentDao.insertRecruit(dto);
		return len > 0?true:false;
	}
	
	public boolean recruitUpdating(EmploymentBoardDto dto) {
		int len = employmentDao.recruitUpdating(dto);
		return len > 0?true:false;
	}
	
	public boolean recruitDelete(int empBoardSeq) {
		int len = employmentDao.recruitDelete(empBoardSeq);
		return len > 0?true:false;
	}
	
}
