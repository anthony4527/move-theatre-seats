package com.techreturn;

public class SeatsCheck {

    public static MovieSeat SearchNextAvailSeat(Cinema cinema){
        boolean found = false;
        boolean EndofAllSeats = false;
        int rowNum = 0;
        int rowIdx= 0;

        while ((!found) && (!EndofAllSeats)) {
            while (rowNum< cinema.MaxRowSeat) {
                if (cinema.listOfSeats[rowIdx][rowNum].sts.equals(STATUS.AVAIL)) {
                    found = true;
                    break;
                } else {
                    rowNum++;
                }
            }
            if (!found) {
                rowIdx++;
                if (rowIdx >=cinema.MaxRow){
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
            return cinema.listOfSeats[rowIdx][rowNum];
    }

    public static int remainingSeats(MovieSeat nextSeat, Cinema cinema) {
        int remainingRows =0;
        int availOnRow = 0;

        if (nextSeat == null){
            return 0;
            //get seat number and check if remaining
        }else {
            availOnRow = cinema.MaxRowSeat - nextSeat.rowNum +1; //( Character.getNumericValue(nextSeat.charAt(1))-1);
            remainingRows = cinema.MaxRow - cinema.mapRowIndex(nextSeat.rowLabel)-1;

            return (remainingRows * cinema.MaxRowSeat + availOnRow);
        }
    }

    public static boolean chkSeatAvail(String seatNo, Cinema cinema){
        //this method check if a specific seat e.g. A5 is available

        STATUS sts;
        int rowIndex =0;
        //identify the row label and number, and perform lookup
        String rowLabel = seatNo.substring(0,1);
        int rowNum = Character.getNumericValue(seatNo.charAt(1))-1;
        //lookup list of seat to check seat status
        rowIndex = cinema.mapRowIndex(rowLabel);

        if (cinema.listOfSeats[rowIndex][rowNum].sts.equals(STATUS.AVAIL) ){
            return true;
        }
        else {
            return false;
        }
    }
}
