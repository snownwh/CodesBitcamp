package com.palette.model;

public class InterSmallDto {

    private int interSmallSeq;
    private int interBigSeq;
    private String smallName;


    public InterSmallDto() {
    }

    public InterSmallDto(int interSmallSeq, int interBigSeq, String smallName) {
        this.interSmallSeq = interSmallSeq;
        this.interBigSeq = interBigSeq;
        this.smallName = smallName;
    }

    public int getInterSmallSeq() {
        return this.interSmallSeq;
    }

    public void setInterSmallSeq(int interSmallSeq) {
        this.interSmallSeq = interSmallSeq;
    }

    public int getInterBigSeq() {
        return this.interBigSeq;
    }

    public void setInterBigSeq(int interBigSeq) {
        this.interBigSeq = interBigSeq;
    }

    public String getSmallName() {
        return this.smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public InterSmallDto interSmallSeq(int interSmallSeq) {
        this.interSmallSeq = interSmallSeq;
        return this;
    }

    public InterSmallDto interBigSeq(int interBigSeq) {
        this.interBigSeq = interBigSeq;
        return this;
    }

    public InterSmallDto smallName(String smallName) {
        this.smallName = smallName;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " interSmallSeq='" + getInterSmallSeq() + "'" +
            ", interBigSeq='" + getInterBigSeq() + "'" +
            ", smallName='" + getSmallName() + "'" +
            "}";
    }

}