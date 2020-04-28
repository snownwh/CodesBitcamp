package com.palette.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.palette.dao.MemberCVDao;
import com.palette.model.BoardParams;
import com.palette.model.MemberCVDto;
import com.palette.model.MemberDto;



@Service
@Transactional
public class MemberCVService {
	
	@Autowired
	MemberCVDao memberCVDao;
	
	public boolean insertCV(MemberCVDto dto) {
		int len = memberCVDao.insertCV(dto);
		return len>0?true:false;
	}
	
	public void CVNumChange(int seq) {
		memberCVDao.CVNumChange(seq);
	}
	
	public ArrayList<MemberCVDto> getCVPagingList(BoardParams boardParams){
		ArrayList<MemberCVDto> list = memberCVDao.getCVPagingList(boardParams);
		
		return list;
	}
	
	public int CVList(BoardParams boardParams) {
		return memberCVDao.CVList(boardParams);
	}
	
	public MemberCVDto getOneCV(int cvSeq) {
		return memberCVDao.getOneCV(cvSeq);
	}
	
	public void readCount(int cvSeq) {
		memberCVDao.readCount(cvSeq);
	}
	
	public MemberDto oneMember(int memberSeq) {
		return memberCVDao.oneMember(memberSeq);
	}
	
	public boolean updateCV(MemberCVDto dto){
		int len = memberCVDao.updateCV(dto);
		return len > 0?true:false;
	}
	
	public MemberCVDto getOneCVByMemberSeq(int memberSeq) {
		return memberCVDao.getOneCVByMemberSeq(memberSeq);
	}
}
