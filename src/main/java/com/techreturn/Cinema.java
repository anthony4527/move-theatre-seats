package com.techreturn;

public class Cinema {
    MovieSeat[][] listOfSeats = new MovieSeat[3][5];
    //init all seat as available
    public Cinema(){
        // set each
        String label = " ";
        for (int i=0; i< 3; i++){
            switch (i) {
                case 0:
                    label = "A";
                    break;
                case 1:
                    label = "B";
                    break;
                case 2:
                    label = "C";
                    break;
            }
            for (int j=0; j<5; j++){
                this.listOfSeats[i][j] = new MovieSeat(label,j+1);
            }
        }
    }

    public int mapRowIndex( String seatNo){
        String rowLabel = seatNo.substring(0,1);
        return (switch (rowLabel) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            default -> -1;
        });
    }
    public boolean chkSeatAvail(String seatNo){

        STATUS sts;
        int rowIndex =0;
        //identify the row label and number, and perform lookup
        String rowLabel = seatNo.substring(0,1);
        int rowNum = Character.getNumericValue(seatNo.charAt(1))-1;
        //lookup list of seat to check seat status
        rowIndex = mapRowIndex(seatNo);

        if (listOfSeats[rowIndex][rowNum].sts.equals(STATUS.AVAIL) ){
            return true;
        }
        else {
            return false;
        }

    }

    public void allocateSeat(String seatNo) {
        int RowIndex = mapRowIndex(seatNo);
        int rowNum = Character.getNumericValue(seatNo.charAt(1))-1;

        this.listOfSeats[RowIndex][rowNum].sts = STATUS.ALLOCATED;

    }

    public void allocateNextAvailSeat(int count) {
        //read each row and look for next available seat
        boolean found = false;
        boolean parseAllRows = false;
        int rowNum = 0;
        int rowIdx= 0;

        while (!found){
            while (rowNum< 5) {
                if (this.listOfSeats[rowIdx][rowNum].sts.equals(STATUS.AVAIL)) {
                    found = true;
                    break;
                } else {
                    rowNum++;
                }
            }
            if (!found) {
                rowIdx++;
                rowNum = 0;
            }
        }

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
            if (rowNum >= 5){
                rowIdx++;
                rowNum = 0;
            }
            if (rowIdx >=3) {
                EndofAllSeats =true;
            }
        }
    }
}
