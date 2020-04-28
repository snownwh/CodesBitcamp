package com.palette.model;

import java.io.Serializable;

/*`MEMBER_INTERREST_SEQ` INT(8) NOT NULL AUTO_INCREMENT,
`MEMBER_SEQ` INT(8) NULL,
`BIG_SEQ` INT(8) NULL,
`SMALL_SEQ` INT(8) NULL,
`DEL` INT(8) NULL,*/
public class MemberInterDto implements Serializable {
	private int memberInterrestSeq;
	private int memberSeq;
	private int bigSeq;
	private int smallSeq;
	private int del;
	
	public MemberInterDto() {
	}
	
	public MemberInterDto(int memberInterrestSeq, int memberSeq, int bigSeq, int smallSeq, int del) {
		super();
		this.memberInterrestSeq = memberInterrestSeq;
		this.memberSeq = memberSeq;
		this.bigSeq = bigSeq;
		this.smallSeq = smallSeq;
		this.del = del;
	}

	public int getMemberInterrestSeq() {
		return memberInterrestSeq;
	}

	public void setMemberInterrestSeq(int memberInterrestSeq) {
		this.memberInterrestSeq = memberInterrestSeq;
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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "MemberInterDto [memberInterrestSeq=" + memberInterrestSeq + ", memberSeq=" + memberSeq + ", bigSeq="
				+ bigSeq + ", smallSeq=" + smallSeq + ", del=" + del + "]";
	}
	
	
	
	
}
