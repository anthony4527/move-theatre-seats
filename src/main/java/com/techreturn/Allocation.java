package com.techreturn;

public class Allocation {
    public static void allocateNextAvailSeat(int count, Cinema cinema) {
        int rowNum = 0;
        int rowIdx= 0;

        //search next available seat
        // check remaining available seats
        // if count <= remaining then allocate else do not allocate and print console error
        MovieSeat nextSeat = SeatsCheck.SearchNextAvailSeat(cinema);
        int remains = SeatsCheck.remainingSeats(nextSeat, cinema);
        rowIdx = cinema.mapRowIndex(nextSeat.rowLabel);
        rowNum = nextSeat.rowNum - 1;

        if (remains >= count) {
            boolean     EndofAllSeats = false;
            int times = 1;
            while ((!EndofAllSeats) && (times <= count)) {

                //the following method allocates a seat - only if check seat is available
                allocateSeatByIndex(rowIdx,rowNum, cinema);

                times++;
                rowNum++;
                //if rowNum reaches end, allocate from next row left most
                if (rowNum >= cinema.MaxRowSeat){
                    rowIdx++;
                    rowNum = 0;
                }
                if (rowIdx >= cinema.MaxRow) {
                    EndofAllSeats =true;
                }
            }
        }
    }

    public static void allocateSeatByIndex(int rowIdx, int rowNum, Cinema cinema) {

        if (cinema.listOfSeats[rowIdx][rowNum].sts == STATUS.AVAIL){
            cinema.listOfSeats[rowIdx][rowNum].sts = STATUS.ALLOCATED;
            System.out.println("Next available seat is "+cinema.listOfSeats[rowIdx][rowNum].rowLabel +
                    String.valueOf(cinema.listOfSeats[rowIdx][rowNum].rowNum) +"...ALLOCATED");
        } else {
            System.out.println("seat not available, allocation not done!!  ");
        }
    }

    public static void allocateSeat(String seatNo, Cinema cinema) {
        // this method allocate a seat by reference of String seat no.
        // map the string e.g. "B2" to the array row and number
        int rowIndex = cinema.mapRowIndex(seatNo.substring(0,1));
        int rowNum = Character.getNumericValue(seatNo.charAt(1))-1;

        allocateSeatByIndex(rowIndex, rowNum, cinema);
    }
}
