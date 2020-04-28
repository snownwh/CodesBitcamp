package com.palette.model;

public class MemberInterParam {
	private int memberSeq;
	private int bigSeq;
	private int smallSeq;
	
	public MemberInterParam() {
	}

	public MemberInterParam(int memberSeq, int bigSeq, int smallSeq) {
		super();
		this.memberSeq = memberSeq;
		this.bigSeq = bigSeq;
		this.smallSeq = smallSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public int getBigSeq() {
		return bigSeq;
	}

	public void setBigSeq(int bigSeq) {
		this.bigSeq = bigSeq;
	}

	public int getSmallSeq() {
		return smallSeq;
	}

	public void setSmallSeq(int smallSeq) {
		this.smallSeq = smallSeq;
	}

	@Override
	public String toString() {
		return "MemberInterParam [memberSeq=" + memberSeq + ", bigSeq=" + bigSeq + ", smallSeq=" + smallSeq + "]";
	}

	
	
	
	
}
