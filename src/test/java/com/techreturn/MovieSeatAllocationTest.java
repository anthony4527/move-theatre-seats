package com.techreturn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieSeatAllocationTest {

    @Test
    public void testCinemaSeatAvailability(){
        Cinema cinema = new Cinema();
        String seatNo = "A1";
        assertTrue(cinema.chkSeatAvail(seatNo));
    }

    @Test
    public void testSeatAllocated(){
        String seatNo = "A1";
        Cinema cinema = new Cinema();
        cinema.allocateSeat(seatNo);
        assertTrue(!cinema.chkSeatAvail(seatNo));
    }

    @Test
    public void AllocatedNextAvailableTest(){
        //String seatNo = "A1";
        int count =1;
        Cinema cinema = new Cinema();
        cinema.allocateSeat("A1");
        Allocation.allocateNextAvailSeat(count, cinema);
        assertTrue(!cinema.chkSeatAvail("A2"));
    }
    @Test
    public void AllocatedTwoAvailableTest(){
        //String seatNo = "A1";
        int count=1;
        Cinema cinema = new Cinema();
        cinema.allocateSeat("A1");
        cinema.allocateSeat("A2");
        Allocation.allocateNextAvailSeat(count, cinema);
        assertTrue(!cinema.chkSeatAvail("A3"));
    }

    @Test
    public void AllocateMultipleSeatsTest(){
        //String seatNo = "A1";
        int  count=3;
        Cinema cinema = new Cinema();
        Allocation.allocateNextAvailSeat(count, cinema);
        assertTrue(!cinema.chkSeatAvail("A1"));
        assertTrue(!cinema.chkSeatAvail("A2"));
    }

    @Test
    public void AllocateOnTwoRowsTest(){
        int  count=3;
        Cinema cinema = new Cinema();
        // allocate three seats for one customers
        //allocte another three seats for another customers
        Allocation.allocateNextAvailSeat(count, cinema);
        Allocation.allocateNextAvailSeat(count, cinema);
        //allocated upto B1,next available is B3
        assertTrue(!cinema.chkSeatAvail("B1"));
        assertTrue(cinema.chkSeatAvail("B2"));
    }
    @Test
    public void AllocateUptoThreeRows(){
        int  count=3;
        Cinema cinema = new Cinema();
        Allocation.allocateNextAvailSeat(count, cinema);//upto A3
        Allocation.allocateNextAvailSeat(count, cinema);//upto B1
        Allocation.allocateNextAvailSeat(count, cinema);//upto B4
        Allocation.allocateNextAvailSeat(count, cinema);//upto C2

        assertTrue(!cinema.chkSeatAvail("C2"));
        assertTrue(cinema.chkSeatAvail("C3"));
    }

    @Test
    public void AllocateUptoFullSeat(){
        //int  count=3;
        Cinema cinema = new Cinema();
        Allocation.allocateNextAvailSeat(3, cinema); //upto A3
        Allocation.allocateNextAvailSeat(3, cinema); //upto B1
        Allocation.allocateNextAvailSeat(3, cinema); //upto B4
        Allocation.allocateNextAvailSeat(3, cinema); //upto c2
        Allocation.allocateNextAvailSeat(3, cinema); //upto c5
        //cinema.allocateNextAvailSeat(3); //upto c5

        assertTrue(!cinema.chkSeatAvail("C5"));
//        assertTrue(cinema.chkSeatAvail("B5"));
    }
    @Test
    public void RejectforNoMoreSeat(){
        //int  count=3;
        Cinema cinema = new Cinema();

        Allocation.allocateNextAvailSeat(3, cinema); //upto A3
        Allocation.allocateNextAvailSeat(3, cinema); //upto B1
        Allocation.allocateNextAvailSeat(3, cinema); //upto B4
        Allocation.allocateNextAvailSeat(3, cinema); //upto c2
        Allocation.allocateNextAvailSeat(2, cinema); //upto c4


        assertEquals("NO", cinema.AcceptRequestSeat(3, cinema.listOfSeats[2][4]));
//        assertTrue(cinema.chkSeatAvail("B5"));
    }

    //Test boundary case of last allocated is C4
    @Test
    public void AllocateLastSeatTes() {
        //int  count=3;
        Cinema cinema = new Cinema();
        for (int i = 0; i < 4; i++) {
            Allocation.allocateNextAvailSeat(3, cinema); //upto C1
        }
        Allocation.allocateNextAvailSeat(2, cinema); //upto C3
        assertEquals(1, SeatsCheck.remainingSeats(cinema.listOfSeats[2][4],cinema));
    }
}
