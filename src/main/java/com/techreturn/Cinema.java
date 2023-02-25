package com.techreturn;

import java.util.Map;
import java.util.HashMap;


public class Cinema {
    public final int MaxRow = 3;
    public final int MaxRowSeat = 5;

    MovieSeat[][] listOfSeats = new MovieSeat[MaxRow][MaxRowSeat];
    // a map of the array index to label
    Map<Integer, String> labelMap = new HashMap<Integer, String>();

    //init all seat as available
    public Cinema(){
        String label;
        labelMap.put(0, "A");
        labelMap.put(1, "B");
        labelMap.put(2, "C");
        // set each
        for (int i=0; i< MaxRow; i++){
            label = labelMap.get(i);
            for (int j=0; j<MaxRowSeat; j++){
                this.listOfSeats[i][j] = new MovieSeat(label,j+1);
            }
        }
    }

    public int mapRowIndex( String rowLabel){
        //lookup hashmap to find the index for row A,B, & C
        int rowIdx = -1;
        for (int i : labelMap.keySet()){
            if (labelMap.get(i).equals(rowLabel)){
                rowIdx = i;
            }
        }
        return rowIdx;
    }
    public boolean chkSeatAvail(String seatNo){

        STATUS sts;
        int rowIndex =0;
        //identify the row label and number, and perform lookup
        String rowLabel = seatNo.substring(0,1);
        int rowNum = Character.getNumericValue(seatNo.charAt(1))-1;
        //lookup list of seat to check seat status
        rowIndex = mapRowIndex(rowLabel);

        if (listOfSeats[rowIndex][rowNum].sts.equals(STATUS.AVAIL) ){
            return true;
        }
        else {
            return false;
        }
    }

    public void allocateSeat(String seatNo) {
        int RowIndex = mapRowIndex(seatNo.substring(0,1));
        int rowNum = Character.getNumericValue(seatNo.charAt(1))-1;

        if (this.listOfSeats[RowIndex][rowNum].sts == STATUS.AVAIL){
            this.listOfSeats[RowIndex][rowNum].sts = STATUS.ALLOCATED;
        } else {
            System.out.println("seat not available, allocation not done!!  ");
        }
    }

    public MovieSeat SearchNextAvailSeat(){
        boolean found = false;
        boolean EndofAllSeats = false;
        int rowNum = 0;
        int rowIdx= 0;

        while ((!found) && (!EndofAllSeats)) {
            while (rowNum< MaxRowSeat) {
                if (this.listOfSeats[rowIdx][rowNum].sts.equals(STATUS.AVAIL)) {
                    found = true;
                    break;
                } else {
                    rowNum++;
                }
            }
            if (!found) {
                rowIdx++;
                if (rowIdx >=MaxRow){
                    EndofAllSeats = true;
                }else {
                    rowNum = 0;
                }
            }
        }
        //format seat no. using rowIdx & rowNum
        if (EndofAllSeats == true){
            return null;
        }
        else
            return this.listOfSeats[rowIdx][rowNum];
    }

    public void allocateNextAvailSeat(int count) {
        int rowNum = 0;
        int rowIdx= 0;

        //search next available seat
        // check remaining available seats
        // if count <= remaining then allocate else throw error
        MovieSeat nextSeat = SearchNextAvailSeat();
        int remains = remainingSeats(nextSeat);
        rowIdx = mapRowIndex(nextSeat.rowLabel) ;
        rowNum = nextSeat.rowNum -1;
        if (remains >= count) {
            boolean     EndofAllSeats = false;
            int times = 1;
            while ((!EndofAllSeats) && (times <= count)) {
                if ( this.listOfSeats[rowIdx][rowNum].sts.equals(STATUS.AVAIL)){
                    this.listOfSeats[rowIdx][rowNum].sts = STATUS.ALLOCATED;
                    System.out.println("Next available seat is "+this.listOfSeats[rowIdx][rowNum].rowLabel +
                            String.valueOf(this.listOfSeats[rowIdx][rowNum].rowNum) +"...ALLOCATED");
                } else {
                    System.out.println("Unexpected error - next seat not available");
                }
                times++;
                rowNum++;
                //if rowNum reaches end, allocate from next row left most
                if (rowNum >= MaxRowSeat){
                    rowIdx++;
                    rowNum = 0;
                }
                if (rowIdx >=MaxRow) {
                    EndofAllSeats =true;
                }
            }
        }

    }

    public int remainingSeats(MovieSeat nextSeat) {
        int remainingRows =0;
        int availOnRow = 0;

        if (nextSeat == null){
            return 0;
            //get seat number and check if remaining
        }else {
            availOnRow = MaxRowSeat - nextSeat.rowNum +1; //( Character.getNumericValue(nextSeat.charAt(1))-1);
            remainingRows = MaxRow - mapRowIndex(nextSeat.rowLabel)-1;

            return (remainingRows*MaxRowSeat + availOnRow);
        }

    }

    public String AcceptRequestSeat(int i, MovieSeat seat) {
        if (remainingSeats(seat) >= i){
            return ("YES");
        }
        else {
            return ("NO");
        }
    }
}
