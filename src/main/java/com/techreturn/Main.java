package com.techreturn;

import java.text.MessageFormat;

public class Main {

    public static void main(String[] args) {
        boolean fullyBooked = false;
        final int MaxPerRequest = 3;
        int count;
        int remains;
        //inform start movie seat allocation..
        Cinema cinema = new Cinema();
        System.out.println("***Cinnamon Movie Ticket Allocation start ....\n");

        remains = cinema.remainingSeats();
        // while not fully booked, generate random no. of tickets request and call allocate next method
        while (!fullyBooked) {
               count = (int) Math.floor(Math.random() * MaxPerRequest);
               System.out.println(">> request is "+count);
               if (count>0) {
                   // check if any more seat left, and set fully Booked
                   //if count is less than remaining seat, proceed to book, otherwise reject
                   if (count <= remains) {
                       cinema.allocateNextAvailSeat(count);
                       remains = cinema.remainingSeats();
                       if (cinema.remainingSeats() == 0){
                           fullyBooked =true;
                       }
                   } else {
                       System.out.println(
                               MessageFormat.format("??? Seat remains  is {0} and request is {1}, cannot allocate! ", String.valueOf(remains), String.valueOf(count)));
                   }

               }

        }
        System.out.println("### All seats allocated... program will stop");
    }
}