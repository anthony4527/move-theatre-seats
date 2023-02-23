package com.techreturn;

public class MovieSeat {
    String rowLabel;
    int rowNum;
    STATUS sts;

    public MovieSeat (String rowLabel, int rowNum ){
        this.rowLabel = rowLabel;
        this.rowNum = rowNum;
        this.sts = STATUS.AVAIL;
    }
    public void allocate(){
        this.sts = STATUS.ALLOCATED;
    }
}
