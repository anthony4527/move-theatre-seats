package com.techreturn;

import org.junit.jupiter.api.Test;

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
        cinema.allocateNextAvailSeat(count);
        assertTrue(!cinema.chkSeatAvail("A2"));
    }
    @Test
    public void AllocatedTwoAvailableTest(){
        //String seatNo = "A1";
        int count=1;
        Cinema cinema = new Cinema();
        cinema.allocateSeat("A1");
        cinema.allocateSeat("A2");
        cinema.allocateNextAvailSeat(count);
        assertTrue(!cinema.chkSeatAvail("A3"));
    }

    @Test
    public void AllocateMultipleSeatsTest(){
        //String seatNo = "A1";
        int  count=3;
        Cinema cinema = new Cinema();
        cinema.allocateNextAvailSeat(count);
        assertTrue(!cinema.chkSeatAvail("A1"));
        assertTrue(!cinema.chkSeatAvail("A2"));
    }

    @Test
    public void AllocateOnTwoRowsTest(){
        int  count=3;
        Cinema cinema = new Cinema();
        // allocate three seats for one customers
        //allocte another three seats for another customers
        cinema.allocateNextAvailSeat(count);
        cinema.allocateNextAvailSeat(count);
        //allocated upto B1,next available is B3
        assertTrue(!cinema.chkSeatAvail("B1"));
        assertTrue(cinema.chkSeatAvail("B2"));
    }
    @Test
    public void AllocateUptoThreeRows(){
        int  count=3;
        Cinema cinema = new Cinema();
        cinema.allocateNextAvailSeat(count); //upto A3
        cinema.allocateNextAvailSeat(count); //upto B1
        cinema.allocateNextAvailSeat(count); //upto B4
        cinema.allocateNextAvailSeat(count); //upto c2

        assertTrue(!cinema.chkSeatAvail("C2"));
        assertTrue(cinema.chkSeatAvail("C3"));
    }

    @Test
    public void AllocateUptoFullSeat(){
        //int  count=3;
        Cinema cinema = new Cinema();
        cinema.allocateNextAvailSeat(3); //upto A3
        cinema.allocateNextAvailSeat(3); //upto B1
        cinema.allocateNextAvailSeat(3); //upto B4
        cinema.allocateNextAvailSeat(3); //upto c2
        cinema.allocateNextAvailSeat(2); //upto c4

        assertTrue(!cinema.chkSeatAvail("B4"));
        assertTrue(cinema.chkSeatAvail("B5"));
    }
}
