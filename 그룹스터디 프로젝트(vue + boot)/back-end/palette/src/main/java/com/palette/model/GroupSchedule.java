package com.palette.model;

public class GroupSchedule {
	private int groupScheduleSeq;
	private int groupInfoSeq;
	private int sunday;
	private int monday;
	private int tuesday;
	private int wednesday;
	private int thursday;
	private int friday;
	private int saturday;
	
	private int memberSeq;
	private String nowDate;
	
	public GroupSchedule() {
		
	}

	public GroupSchedule(int groupScheduleSeq, int groupInfoSeq, int sunday, int monday, int tuesday, int wednesday,
			int thursday, int friday, int saturday, int memberSeq, String nowDate) {
		super();
		this.groupScheduleSeq = groupScheduleSeq;
		this.groupInfoSeq = groupInfoSeq;
		this.sunday = sunday;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.memberSeq = memberSeq;
		this.nowDate = nowDate;
	}

	@Override
	public String toString() {
		return "GroupSchedule [groupScheduleSeq=" + groupScheduleSeq + ", groupInfoSeq=" + groupInfoSeq + ", sunday="
				+ sunday + ", monday=" + monday + ", tuesday=" + tuesday + ", wednesday=" + wednesday + ", thursday="
				+ thursday + ", friday=" + friday + ", saturday=" + saturday + ", memberSeq=" + memberSeq + ", nowDate="
				+ nowDate + "]";
	}

	
	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public int getGroupScheduleSeq() {
		return groupScheduleSeq;
	}

	public void setGroupScheduleSeq(int groupScheduleSeq) {
		this.groupScheduleSeq = groupScheduleSeq;
	}

	public int getGroupInfoSeq() {
		return groupInfoSeq;
	}

	public void setGroupInfoSeq(int groupInfoSeq) {
		this.groupInfoSeq = groupInfoSeq;
	}

	public int getSunday() {
		return sunday;
	}

	public void setSunday(int sunday) {
		this.sunday = sunday;
	}

	public int getMonday() {
		return monday;
	}

	public void setMonday(int monday) {
		this.monday = monday;
	}

	public int getTuesday() {
		return tuesday;
	}

	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}

	public int getWednesday() {
		return wednesday;
	}

	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}

	public int getThursday() {
		return thursday;
	}

	public void setThursday(int thursday) {
		this.thursday = thursday;
	}

	public int getFriday() {
		return friday;
	}

	public void setFriday(int friday) {
		this.friday = friday;
	}

	public int getSaturday() {
		return saturday;
	}

	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}
	
	
	
}
