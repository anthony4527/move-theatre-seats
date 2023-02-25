package com.techreturn;

public class MovieSeat {
    protected String rowLabel;
    protected int rowNum;
    protected STATUS sts;

    public MovieSeat (String rowLabel, int rowNum ){
        this.rowLabel = rowLabel;
        this.rowNum = rowNum;
        this.sts = STATUS.AVAIL;
    }

}
