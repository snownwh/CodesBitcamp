package com.palette.model;

import java.util.List;

public class InterBigDto {

    private int interBigSeq;
    private String bigName;

    private List<InterSmallDto> interSmallDtos;


    public InterBigDto() {
    }

    public InterBigDto(int interBigSeq, String bigName, List<InterSmallDto> interSmallDtos) {
        this.interBigSeq = interBigSeq;
        this.bigName = bigName;
        this.interSmallDtos = interSmallDtos;
    }

    public int getInterBigSeq() {
        return this.interBigSeq;
    }

    public void setInterBigSeq(int interBigSeq) {
        this.interBigSeq = interBigSeq;
    }

    public String getBigName() {
        return this.bigName;
    }

    public void setBigName(String bigName) {
        this.bigName = bigName;
    }

    public List<InterSmallDto> getInterSmallDtos() {
        return this.interSmallDtos;
    }

    public void setInterSmallDtos(List<InterSmallDto> interSmallDtos) {
        this.interSmallDtos = interSmallDtos;
    }

    public InterBigDto interBigSeq(int interBigSeq) {
        this.interBigSeq = interBigSeq;
        return this;
    }

    public InterBigDto bigName(String bigName) {
        this.bigName = bigName;
        return this;
    }

    public InterBigDto interSmallDtos(List<InterSmallDto> interSmallDtos) {
        this.interSmallDtos = interSmallDtos;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " interBigSeq='" + getInterBigSeq() + "'" +
            ", bigName='" + getBigName() + "'" +
            ", interSmallDtos='" + getInterSmallDtos() + "'" +
            "}";
    }

}