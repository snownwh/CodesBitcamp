package com.palette.model;

public class BoardParams {
	
	private int page;		// 1	2
	private int limit;	// 5
	private int start;			// 0	5
	
	private int groupSeq;
	private String keyWord;
	private String searchWord;
	
	public BoardParams() {
	
	}

	public BoardParams(int page, int limit, int start, int groupSeq, String keyWord, String searchWord) {
		super();
		this.page = page;
		this.limit = limit;
		this.start = start;
		this.groupSeq = groupSeq;
		this.keyWord = keyWord;
		this.searchWord = searchWord;
	}

	@Override
	public String toString() {
		return "BoardParams [page=" + page + ", limit=" + limit + ", start=" + start + ", groupSeq=" + groupSeq
				+ ", keyWord=" + keyWord + ", searchWord=" + searchWord + "]";
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getGroupSeq() {
		return groupSeq;
	}

	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	
	
	
	
	
}
