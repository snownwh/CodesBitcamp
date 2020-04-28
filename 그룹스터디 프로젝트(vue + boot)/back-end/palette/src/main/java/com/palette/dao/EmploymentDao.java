package com.palette.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.palette.model.EmploymentBoardDto;

@Mapper
@Repository
public interface EmploymentDao {

	List<EmploymentBoardDto> getAllRecuritingInfo();

	EmploymentBoardDto getOneRecruit(int empBoardSeq);
	
	void readCount(int empBoardSeq);
	
	int insertRecruit(EmploymentBoardDto dto);
	
	int recruitUpdating(EmploymentBoardDto dto);
	
	int recruitDelete(int empBoardSeq);

}
