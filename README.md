# move-theatre-seats
Movie Theatre Seatings Allocation project

This package will help the Cinnamon Cinemas Movie Theatre Manager to allocate seats to customers. The User Story from the Manager is as follows:
- GIVEN a customer wants to request some tickets
- WHEN they request a number of seats between 1 and 3 for a movie
- THEN the customer should be allocated the required number of seatsfrom the available seats on the seating plan
- ND the seats should be recorded as allocated

The program in this repo allocates seats based on a random integer “number of seats” between 1 and 3. It allocates the required number of seats from the available seats starting from seat A1 and filling the auditorium from
left to right, front to back. All of the seats are available for sale when the program starts. The program continues to allocate a random number of seats until it finds there are not enough seats, then it will stop.

How I developed the program
===========================
I used the TDD approach, to write the test fail cases first, then develop the codes to make them pass, and final refactor. 

I have developed the following classes:
- Cinema class which constructs all the seats by rows and number, and init with AVAIL status
- MovieSeat class which have attributes of seat label (A,B or C), seat number and status.
- Enum Status, with two states currently (AVAIL & ALLOCATED)
- SeatsCheck class, which is responsible to check seat availability; it includes methods to search for the next available seat, and meethod to return number of seats remaining.
- Allocation class, which will perform the allocation, by recording a seat as 'ALLOCATED'. The allocation will call SeatCheck to compare the allocation request against number of remaining seats. 
If the remaining seats are not sufficient, the request will not be processed.

The Main program will generate a random request for number of seats for allocation. By running the main(), it will shows the seats which are allocated. I have added the logic that to continue generate random number until the last seatis allocated  i.e.
until all seats are fully allocated.

All the classes have been refactored, with D.R.Y. and Single responsibility principles applied, by breaking down the initial codes into smaller and manageable classes.

Future development
=================
The classes have been developed such that they can be easily updated to support different sizes of cinema seatings. The allocation class can also be updated to handle additional rules for allocaion e.g. it includes method to reserve a specific seat, which is dffierent from the current sequentail allocation scheme. Additional search method can be added to the SeatsCheck class to lookup available seats when seats are not reserved by sequential rule (e.g. customers select abailable seats).
