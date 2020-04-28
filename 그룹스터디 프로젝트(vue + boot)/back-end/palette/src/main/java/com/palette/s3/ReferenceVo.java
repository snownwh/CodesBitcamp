package com.palette.s3;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ReferenceVo {

    private int groupInfoSeq;
    private int boardSeq;
    private int memberSeq;
    private String memberId;
    private String title;
    private String content;
    private List<MultipartFile> files;


    public ReferenceVo() {
    }

    public ReferenceVo(int groupInfoSeq, int boardSeq, int memberSeq, String memberId, String title, String content, List<MultipartFile> files) {
        this.groupInfoSeq = groupInfoSeq;
        this.boardSeq = boardSeq;
        this.memberSeq = memberSeq;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.files = files;
    }

    public int getGroupInfoSeq() {
        return this.groupInfoSeq;
    }

    public void setGroupInfoSeq(int groupInfoSeq) {
        this.groupInfoSeq = groupInfoSeq;
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

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MultipartFile> getFiles() {
        return this.files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public ReferenceVo groupInfoSeq(int groupInfoSeq) {
        this.groupInfoSeq = groupInfoSeq;
        return this;
    }

    public ReferenceVo boardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
        return this;
    }

    public ReferenceVo memberSeq(int memberSeq) {
        this.memberSeq = memberSeq;
        return this;
    }

    public ReferenceVo memberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

    public ReferenceVo title(String title) {
        this.title = title;
        return this;
    }

    public ReferenceVo content(String content) {
        this.content = content;
        return this;
    }

    public ReferenceVo files(List<MultipartFile> files) {
        this.files = files;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " groupInfoSeq='" + getGroupInfoSeq() + "'" +
            ", boardSeq='" + getBoardSeq() + "'" +
            ", memberSeq='" + getMemberSeq() + "'" +
            ", memberId='" + getMemberId() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", files='" + getFiles() + "'" +
            "}";
    }

}