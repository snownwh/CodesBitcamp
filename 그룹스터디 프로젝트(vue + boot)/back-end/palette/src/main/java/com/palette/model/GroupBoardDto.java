package com.palette.model;

public class GroupBoardDto {
	
	private int finalnum;
	
    private int boardSeq;
    private int groupInfoSeq;
    private int memberSeq;
    private int category;
    private String title;
    private String content;

    private String writeDate;
    private int readCount;

    private String image;
    private String fileName;
    private String dbFileName;
    
    private int boardAuth;

    private int del;
    
    private MemberDto memberDto;
    
    // none data//
    private String cvStartDate;
    private String cvEndDate;


    public GroupBoardDto() {
    }

    


	public GroupBoardDto(int finalnum, int boardSeq, int groupInfoSeq, int memberSeq, int category, String title,
			String content, String writeDate, int readCount, String image, String fileName, String dbFileName,
			int boardAuth, int del, MemberDto memberDto, String cvStartDate, String cvEndDate) {
		super();
		this.finalnum = finalnum;
		this.boardSeq = boardSeq;
		this.groupInfoSeq = groupInfoSeq;
		this.memberSeq = memberSeq;
		this.category = category;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.readCount = readCount;
		this.image = image;
		this.fileName = fileName;
		this.dbFileName = dbFileName;
		this.boardAuth = boardAuth;
		this.del = del;
		this.memberDto = memberDto;
		this.cvStartDate = cvStartDate;
		this.cvEndDate = cvEndDate;
	}

	

	public int getFinalnum() {
		return finalnum;
	}




	public void setFinalnum(int finalnum) {
		this.finalnum = finalnum;
	}




	public int getBoardSeq() {
		return boardSeq;
	}




	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}




	public int getGroupInfoSeq() {
		return groupInfoSeq;
	}




	public void setGroupInfoSeq(int groupInfoSeq) {
		this.groupInfoSeq = groupInfoSeq;
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




	public int getBoardAuth() {
		return boardAuth;
	}




	public void setBoardAuth(int boardAuth) {
		this.boardAuth = boardAuth;
	}




	public int getDel() {
		return del;
	}




	public void setDel(int del) {
		this.del = del;
	}




	public MemberDto getMemberDto() {
		return memberDto;
	}




	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
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




	@Override
	public String toString() {
		return "GroupBoardDto [finalnum=" + finalnum + ", boardSeq=" + boardSeq + ", groupInfoSeq=" + groupInfoSeq
				+ ", memberSeq=" + memberSeq + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", writeDate=" + writeDate + ", readCount=" + readCount + ", image=" + image + ", fileName="
				+ fileName + ", dbFileName=" + dbFileName + ", boardAuth=" + boardAuth + ", del=" + del + ", memberDto="
				+ memberDto + ", cvStartDate=" + cvStartDate + ", cvEndDate=" + cvEndDate + "]";
	}

}