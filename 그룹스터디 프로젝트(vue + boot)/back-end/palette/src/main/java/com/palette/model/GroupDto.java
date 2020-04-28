package com.palette.model;

import java.io.Serializable;

/*

CREATE TABLE `GROUP_INFO` (
  `GROUP_INFO_SEQ` int(8) NOT NULL AUTO_INCREMENT,
  `MEMBER_SEQ` int(8) DEFAULT NULL,
  `INTER_BIG_SEQ` int(8) DEFAULT NULL,
  `INTER_SMALL_SEQ` int(8) DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL,
  `INFO` varchar(2000) DEFAULT NULL,
  `MAX_MEMBER` int(2) DEFAULT NULL,
  `IMAGE` varchar(500) DEFAULT NULL,
  `START_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `END_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `DEL` int(1) DEFAULT NULL,
  PRIMARY KEY (`GROUP_INFO_SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

*/
public class GroupDto implements Serializable {

	private int groupInfoSeq;	// group_info_seq;
	private int memberSeq;
	
	private int interBigSeq;
	
	private int interSmallSeq;
	
	private String groupName;
	private String groupLocation;
	private String info;
	private String smallInfo;
	private int maxMember;
	private String image = "";
	private String startDate;
	private String endDate;
	private int permission;
	private int del;

	private GroupSchedule groupSchedule;
	
	
	private InterBigDto interBigDto;
	private InterSmallDto interSmallDto;
	private MemberDto memberDto;
	private GroupMemberDto groupMemberDto;
	
	private int currMember;
	


	public GroupDto() {
	}

	public GroupDto(int groupInfoSeq, int memberSeq, int interBigSeq, int interSmallSeq, String groupName, String groupLocation, String info, String smallInfo, int maxMember, String image, String startDate, String endDate, int permission, int del, GroupSchedule groupSchedule, InterBigDto interBigDto, InterSmallDto interSmallDto, MemberDto memberDto, GroupMemberDto groupMemberDto, int currMember) {
		this.groupInfoSeq = groupInfoSeq;
		this.memberSeq = memberSeq;
		this.interBigSeq = interBigSeq;
		this.interSmallSeq = interSmallSeq;
		this.groupName = groupName;
		this.groupLocation = groupLocation;
		this.info = info;
		this.smallInfo = smallInfo;
		this.maxMember = maxMember;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.permission = permission;
		this.del = del;
		this.groupSchedule = groupSchedule;
		this.interBigDto = interBigDto;
		this.interSmallDto = interSmallDto;
		this.memberDto = memberDto;
		this.groupMemberDto = groupMemberDto;
		this.currMember = currMember;
	}

	public int getGroupInfoSeq() {
		return this.groupInfoSeq;
	}

	public void setGroupInfoSeq(int groupInfoSeq) {
		this.groupInfoSeq = groupInfoSeq;
	}

	public int getMemberSeq() {
		return this.memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public int getInterBigSeq() {
		return this.interBigSeq;
	}

	public void setInterBigSeq(int interBigSeq) {
		this.interBigSeq = interBigSeq;
	}

	public int getInterSmallSeq() {
		return this.interSmallSeq;
	}

	public void setInterSmallSeq(int interSmallSeq) {
		this.interSmallSeq = interSmallSeq;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupLocation() {
		return this.groupLocation;
	}

	public void setGroupLocation(String groupLocation) {
		this.groupLocation = groupLocation;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSmallInfo() {
		return this.smallInfo;
	}

	public void setSmallInfo(String smallInfo) {
		this.smallInfo = smallInfo;
	}

	public int getMaxMember() {
		return this.maxMember;
	}

	public void setMaxMember(int maxMember) {
		this.maxMember = maxMember;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPermission() {
		return this.permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public int getDel() {
		return this.del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public GroupSchedule getGroupSchedule() {
		return this.groupSchedule;
	}

	public void setGroupSchedule(GroupSchedule groupSchedule) {
		this.groupSchedule = groupSchedule;
	}

	public InterBigDto getInterBigDto() {
		return this.interBigDto;
	}

	public void setInterBigDto(InterBigDto interBigDto) {
		this.interBigDto = interBigDto;
	}

	public InterSmallDto getInterSmallDto() {
		return this.interSmallDto;
	}

	public void setInterSmallDto(InterSmallDto interSmallDto) {
		this.interSmallDto = interSmallDto;
	}

	public MemberDto getMemberDto() {
		return this.memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	public GroupMemberDto getGroupMemberDto() {
		return this.groupMemberDto;
	}

	public void setGroupMemberDto(GroupMemberDto groupMemberDto) {
		this.groupMemberDto = groupMemberDto;
	}

	public int getCurrMember() {
		return this.currMember;
	}

	public void setCurrMember(int currMember) {
		this.currMember = currMember;
	}

	public GroupDto groupInfoSeq(int groupInfoSeq) {
		this.groupInfoSeq = groupInfoSeq;
		return this;
	}

	public GroupDto memberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
		return this;
	}

	public GroupDto interBigSeq(int interBigSeq) {
		this.interBigSeq = interBigSeq;
		return this;
	}

	public GroupDto interSmallSeq(int interSmallSeq) {
		this.interSmallSeq = interSmallSeq;
		return this;
	}

	public GroupDto groupName(String groupName) {
		this.groupName = groupName;
		return this;
	}

	public GroupDto groupLocation(String groupLocation) {
		this.groupLocation = groupLocation;
		return this;
	}

	public GroupDto info(String info) {
		this.info = info;
		return this;
	}

	public GroupDto smallInfo(String smallInfo) {
		this.smallInfo = smallInfo;
		return this;
	}

	public GroupDto maxMember(int maxMember) {
		this.maxMember = maxMember;
		return this;
	}

	public GroupDto image(String image) {
		this.image = image;
		return this;
	}

	public GroupDto startDate(String startDate) {
		this.startDate = startDate;
		return this;
	}

	public GroupDto endDate(String endDate) {
		this.endDate = endDate;
		return this;
	}

	public GroupDto permission(int permission) {
		this.permission = permission;
		return this;
	}

	public GroupDto del(int del) {
		this.del = del;
		return this;
	}

	public GroupDto groupSchedule(GroupSchedule groupSchedule) {
		this.groupSchedule = groupSchedule;
		return this;
	}

	public GroupDto interBigDto(InterBigDto interBigDto) {
		this.interBigDto = interBigDto;
		return this;
	}

	public GroupDto interSmallDto(InterSmallDto interSmallDto) {
		this.interSmallDto = interSmallDto;
		return this;
	}

	public GroupDto memberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
		return this;
	}

	public GroupDto groupMemberDto(GroupMemberDto groupMemberDto) {
		this.groupMemberDto = groupMemberDto;
		return this;
	}

	public GroupDto currMember(int currMember) {
		this.currMember = currMember;
		return this;
	}

	@Override
	public String toString() {
		return "{" +
			" groupInfoSeq='" + getGroupInfoSeq() + "'" +
			", memberSeq='" + getMemberSeq() + "'" +
			", interBigSeq='" + getInterBigSeq() + "'" +
			", interSmallSeq='" + getInterSmallSeq() + "'" +
			", groupName='" + getGroupName() + "'" +
			", groupLocation='" + getGroupLocation() + "'" +
			", info='" + getInfo() + "'" +
			", smallInfo='" + getSmallInfo() + "'" +
			", maxMember='" + getMaxMember() + "'" +
			", image='" + getImage() + "'" +
			", startDate='" + getStartDate() + "'" +
			", endDate='" + getEndDate() + "'" +
			", permission='" + getPermission() + "'" +
			", del='" + getDel() + "'" +
			", groupSchedule='" + getGroupSchedule() + "'" +
			", interBigDto='" + getInterBigDto() + "'" +
			", interSmallDto='" + getInterSmallDto() + "'" +
			", memberDto='" + getMemberDto() + "'" +
			", groupMemberDto='" + getGroupMemberDto() + "'" +
			", currMember='" + getCurrMember() + "'" +
			"}";
	}
	
	
}
