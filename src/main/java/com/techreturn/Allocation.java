package com.techreturn;

public class Allocation {
    public static void allocateNextAvailSeat(int count, Cinema cinema) {
        int rowNum = 0;
        int rowIdx= 0;

        //search next available seat
        // check remaining available seats
        // if count <= remaining then allocate else throw error
        MovieSeat nextSeat = SeatsCheck.SearchNextAvailSeat(cinema);
        int remains = SeatsCheck.remainingSeats(nextSeat, cinema);
        rowIdx = cinema.mapRowIndex(nextSeat.rowLabel) ;
        rowNum = nextSeat.rowNum -1;

        if (remains >= count) {
            boolean     EndofAllSeats = false;
            int times = 1;
            while ((!EndofAllSeats) && (times <= count)) {
                if ( cinema.listOfSeats[rowIdx][rowNum].sts.equals(STATUS.AVAIL)){
                    cinema.listOfSeats[rowIdx][rowNum].sts = STATUS.ALLOCATED;
                    System.out.println("Next available seat is "+cinema.listOfSeats[rowIdx][rowNum].rowLabel +
                            String.valueOf(cinema.listOfSeats[rowIdx][rowNum].rowNum) +"...ALLOCATED");
                } else {
                    System.out.println("Unexpected error - next seat not available");
                }
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
}
