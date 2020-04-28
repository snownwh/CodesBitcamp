package com.palette.model;

public class BoardReferenceDto {

    private int boardReferenceSeq;
    private int boardSeq;
    private int memberSeq;
    private String fileName;

    public BoardReferenceDto() {
    }

    public BoardReferenceDto(int boardReferenceSeq, int boardSeq, int memberSeq, String fileName) {
        this.boardReferenceSeq = boardReferenceSeq;
        this.boardSeq = boardSeq;
        this.memberSeq = memberSeq;
        this.fileName = fileName;
    }

    public int getBoardReferenceSeq() {
        return this.boardReferenceSeq;
    }

    public void setBoardReferenceSeq(int boardReferenceSeq) {
        this.boardReferenceSeq = boardReferenceSeq;
    }

    public int getBoardSeq() {
        return this.boardSeq;
    }

    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    public int getMemberSeq() {
        return this.memberSeq;
    }

    public void setMemberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BoardReferenceDto boardReferenceSeq(int boardReferenceSeq) {
        this.boardReferenceSeq = boardReferenceSeq;
        return this;
    }

    public BoardReferenceDto boardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
        return this;
    }

    public BoardReferenceDto memberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
        return this;
    }

    public BoardReferenceDto fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " boardReferenceSeq='" + getBoardReferenceSeq() + "'" +
            ", boardSeq='" + getBoardSeq() + "'" +
            ", memberSeq='" + getMemberSeq() + "'" +
            ", fileName='" + getFileName() + "'" +
            "}";
    }

}