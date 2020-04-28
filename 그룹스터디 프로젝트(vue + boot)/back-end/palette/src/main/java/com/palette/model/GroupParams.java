package com.palette.model;

public class GroupParams {
	private int interBigSeq;
	private int memberSeq;
	
	public GroupParams() {
		
	}
	
	
	public GroupParams(int interBigSeq, int memberSeq) {
		super();
		this.interBigSeq = interBigSeq;
		this.memberSeq = memberSeq;
	}

	@Override
	public String toString() {
		return "GroupParams [interBigSeq=" + interBigSeq + ", memberSeq=" + memberSeq + "]";
	}

	public int getInterBigSeq() {
		return interBigSeq;
	}

	public void setInterBigSeq(int interBigSeq) {
		this.interBigSeq = interBigSeq;
	}


	public int getMemberSeq() {
		return memberSeq;
	}


	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	
}
