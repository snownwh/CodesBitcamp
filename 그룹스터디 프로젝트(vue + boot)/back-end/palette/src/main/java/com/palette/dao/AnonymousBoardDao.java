package com.palette.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.palette.model.AnonymousBoardDto;
import com.palette.model.BoardParams;

@Mapper
@Repository
public interface AnonymousBoardDao {
	
	ArrayList<AnonymousBoardDto> getAnonymousBoardPagingList(BoardParams boardParams);
	
	int anonymousBoardList(BoardParams boardParams);

}
