package com.palette.model;

public class AnonymousBoardDto {
	private int finalnum;
	private String sfinalnum;
	
	private int boardSeq; // BOARD_SEQ
	private int memberSeq; // MEMBER_SEQ

// 게시판 기본 정보
	private int category; // CATEGORY
	private String title; // TITLE
	private String content; // CONTENT
	private String writeDate; // WRITE_DATE
	private int readCount; // READ_COUNT
	private String image; // IMAGE
	private String cvStartDate; // CV_START_DATE
	private String cvEndDate; // CV_END_DATE
	private String fileName; // FILE_NAME
	private String dbFileName; // DB_FILE_NAME
	private int del; // DEL
	private int boardAuth; // BOARD_AUTH
	private long dDay;
	
	private MemberDto memberDto;

	public AnonymousBoardDto() {
	}

	public AnonymousBoardDto(int finalnum, String sfinalnum, int boardSeq, int memberSeq, int category, String title,
			String content, String writeDate, int readCount, String image, String cvStartDate, String cvEndDate,
			String fileName, String dbFileName, int del, int boardAuth, long dDay, MemberDto memberDto) {
		super();
		this.finalnum = finalnum;
		this.sfinalnum = sfinalnum;
		this.boardSeq = boardSeq;
		this.memberSeq = memberSeq;
		this.category = category;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.readCount = readCount;
		this.image = image;
		this.cvStartDate = cvStartDate;
		this.cvEndDate = cvEndDate;
		this.fileName = fileName;
		this.dbFileName = dbFileName;
		this.del = del;
		this.boardAuth = boardAuth;
		this.dDay = dDay;
		this.memberDto = memberDto;
	}

	public int getFinalnum() {
		return finalnum;
	}

	public void setFinalnum(int finalnum) {
		this.finalnum = finalnum;
	}

	public String getSfinalnum() {
		return sfinalnum;
	}

	public void setSfinalnum(String sfinalnum) {
		this.sfinalnum = sfinalnum;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCvStartDate() {
		return cvStartDate;
	}

	public void setCvStartDate(String cvStartDate) {
		this.cvStartDate = cvStartDate;
	}

	public String getCvEndDate() {
		return cvEndDate;
	}

	public void setCvEndDate(String cvEndDate) {
		this.cvEndDate = cvEndDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDbFileName() {
		return dbFileName;
	}

	public void setDbFileName(String dbFileName) {
		this.dbFileName = dbFileName;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getBoardAuth() {
		return boardAuth;
	}

	public void setBoardAuth(int boardAuth) {
		this.boardAuth = boardAuth;
	}

	public long getdDay() {
		return dDay;
	}

	public void setdDay(long dDay) {
		this.dDay = dDay;
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	@Override
	public String toString() {
		return "AnonymousBoardDto [finalnum=" + finalnum + ", sfinalnum=" + sfinalnum + ", boardSeq=" + boardSeq
				+ ", memberSeq=" + memberSeq + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", writeDate=" + writeDate + ", readCount=" + readCount + ", image=" + image + ", cvStartDate="
				+ cvStartDate + ", cvEndDate=" + cvEndDate + ", fileName=" + fileName + ", dbFileName=" + dbFileName
				+ ", del=" + del + ", boardAuth=" + boardAuth + ", dDay=" + dDay + ", memberDto=" + memberDto + "]";
	}

	
	
	
	
	
}
