package com.techreturn;

public class Main {

    public static void main(String[] args) {
        boolean fullyBooked = false;
        final int MaxPerRequest = 3;
        int count;
        //inform start movie seat allocation..
        Cinema cinema = new Cinema();
        System.out.println("***Cinnamon Movie Ticket Allocation start ....\n");


        // while not fully booked, generate random no. of tickets request and call allocate next method
        while (!fullyBooked) {
               count = (int) Math.floor(Math.random() * MaxPerRequest);
               System.out.println(">> count is "+count);
            cinema.allocateNextAvailSeat(count);
            // check if any more seat left, and set fully Booked
            if (cinema.remainingSeats() == 0){
                fullyBooked =true;
            }

        }
        System.out.println("### All seats allocated... program will stop");
    }
}