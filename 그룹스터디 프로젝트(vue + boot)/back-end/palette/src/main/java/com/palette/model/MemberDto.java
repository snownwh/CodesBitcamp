package com.palette.model;

import java.util.List;

/**
 * MemberDto
 */
public class MemberDto {

	private int memberSeq;
	private String memberId;
	private String pwd;
	private String memberName;
	private String address;
	private String interArea;
	private String email;
	private String age;
	private int gender;
	private int myMoney;
	private int cv;
	private String companyInfo;
	private String companyLogo;
	private int auth;
	private int del;
	private int dislike;

	private MemberInterDto memberInterDto;
	private List<InterBigDto> interBigDto;
	private List<InterSmallDto> interSmallDto;
	
	public MemberDto() {
	}

	public MemberDto(int memberSeq, String memberId, String pwd, String memberName, String address, String interArea,
			String email, String age, int gender, int myMoney, int cv, String companyInfo, String companyLogo, int auth,
			int del, int dislike, MemberInterDto memberInterDto, List<InterBigDto> interBigDto,
			List<InterSmallDto> interSmallDto) {
		super();
		this.memberSeq = memberSeq;
		this.memberId = memberId;
		this.pwd = pwd;
		this.memberName = memberName;
		this.address = address;
		this.interArea = interArea;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.myMoney = myMoney;
		this.cv = cv;
		this.companyInfo = companyInfo;
		this.companyLogo = companyLogo;
		this.auth = auth;
		this.del = del;
		this.dislike = dislike;
		this.memberInterDto = memberInterDto;
		this.interBigDto = interBigDto;
		this.interSmallDto = interSmallDto;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInterArea() {
		return interArea;
	}

	public void setInterArea(String interArea) {
		this.interArea = interArea;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getMyMoney() {
		return myMoney;
	}

	public void setMyMoney(int myMoney) {
		this.myMoney = myMoney;
	}

	public int getCv() {
		return cv;
	}

	public void setCv(int cv) {
		this.cv = cv;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public MemberInterDto getMemberInterDto() {
		return memberInterDto;
	}

	public void setMemberInterDto(MemberInterDto memberInterDto) {
		this.memberInterDto = memberInterDto;
	}

	public List<InterBigDto> getInterBigDto() {
		return interBigDto;
	}

	public void setInterBigDto(List<InterBigDto> interBigDto) {
		this.interBigDto = interBigDto;
	}

	public List<InterSmallDto> getInterSmallDto() {
		return interSmallDto;
	}

	public void setInterSmallDto(List<InterSmallDto> interSmallDto) {
		this.interSmallDto = interSmallDto;
	}

	@Override
	public String toString() {
		return "MemberDto [memberSeq=" + memberSeq + ", memberId=" + memberId + ", pwd=" + pwd + ", memberName="
				+ memberName + ", address=" + address + ", interArea=" + interArea + ", email=" + email + ", age=" + age
				+ ", gender=" + gender + ", myMoney=" + myMoney + ", cv=" + cv + ", companyInfo=" + companyInfo
				+ ", companyLogo=" + companyLogo + ", auth=" + auth + ", del=" + del + ", dislike=" + dislike
				+ ", memberInterDto=" + memberInterDto + ", interBigDto=" + interBigDto + ", interSmallDto="
				+ interSmallDto + "]";
	}

	
	
}