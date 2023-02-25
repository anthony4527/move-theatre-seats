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
        //construct all seats for cinema
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


    public String AcceptRequestSeat(int i, MovieSeat seat) {
        if (SeatsCheck.remainingSeats(seat, this) >= i){
            return ("YES");
        }
        else {
            return ("NO");
        }
    }
}
