package com.palette.model;

public class EmploymentBoardDto {
	
	private int finalnum;
	
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
	
//  구인공고 관련
	private String career; // CAREER
	private String education; // EDUCATION
	private String workingType; // WORKING_TYPE
	private String salary; // SALARY
	private String position; // POSITION
	private String workingLocation; // WORKING_LOCATION
	private String webUrl;
	
	
	private MemberDto memberDto;
	
	public EmploymentBoardDto() {
	}

	public EmploymentBoardDto(int finalnum, int boardSeq, int memberSeq, int category, String title, String content,
			String writeDate, int readCount, String image, String cvStartDate, String cvEndDate, String fileName,
			String dbFileName, int del, int boardAuth, long dDay, String career, String education, String workingType,
			String salary, String position, String workingLocation, String webUrl, MemberDto memberDto) {
		super();
		this.finalnum = finalnum;
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
		this.career = career;
		this.education = education;
		this.workingType = workingType;
		this.salary = salary;
		this.position = position;
		this.workingLocation = workingLocation;
		this.webUrl = webUrl;
		this.memberDto = memberDto;
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

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getWorkingType() {
		return workingType;
	}

	public void setWorkingType(String workingType) {
		this.workingType = workingType;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkingLocation() {
		return workingLocation;
	}

	public void setWorkingLocation(String workingLocation) {
		this.workingLocation = workingLocation;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	@Override
	public String toString() {
		return "EmploymentBoardDto [finalnum=" + finalnum + ", boardSeq=" + boardSeq + ", memberSeq=" + memberSeq
				+ ", category=" + category + ", title=" + title + ", content=" + content + ", writeDate=" + writeDate
				+ ", readCount=" + readCount + ", image=" + image + ", cvStartDate=" + cvStartDate + ", cvEndDate="
				+ cvEndDate + ", fileName=" + fileName + ", dbFileName=" + dbFileName + ", del=" + del + ", boardAuth="
				+ boardAuth + ", dDay=" + dDay + ", career=" + career + ", education=" + education + ", workingType="
				+ workingType + ", salary=" + salary + ", position=" + position + ", workingLocation=" + workingLocation
				+ ", webUrl=" + webUrl + ", memberDto=" + memberDto + "]";
	}

	
	

	

	

	

	
	
	
}
