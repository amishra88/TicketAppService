package com.ticketservice.ticketApp;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * main class
 *
 */
public class App {

	public static void main(String[] args) throws InterruptedException {

		try {
			// Initialize Seat Info i.e create seats ,assign seat numbers and assign all
			// seat status to Available
			SeatInfo seats = new SeatInfo();
			seats.createSeats();
			SeatHold seatheld = new SeatHold();
			System.out.println(seats.createSeats());

			while (true) {

				/***
				 * Instantiate ticket service impl class object,this object is created for each
				 * customer in each instance.Take inputs from customer
				 */
				TicketServiceImpl ticketService = new TicketServiceImpl();
				System.out.println("Total number of Available seats: " + ticketService.numSeatsAvailable());
				Scanner sc = new Scanner(System.in);
				System.out.println("Please enter your email Id");
				String email = sc.next();
				// System.out.println("Please enter how many seats you want to book");
				// int n = sc.nextInt();
				int noOfSeats = 0;
				do {
					System.out.println(
							"Please enter how many seats you want to book" + "\n" + "enter a positive number:");
					if (sc.hasNextInt()) {
						noOfSeats = sc.nextInt();
						break;
					} else {
						System.out.println("Sorry that's not a positive number!");
						sc.next();
						continue;
					}
				} while (true);
				System.out.println("Number of seats entered :" + noOfSeats);

				// validate seat count
				if (ticketService.numSeatsAvailable() < noOfSeats) {
					System.out.println(noOfSeats + " number of seats not available");
					System.exit(0);
				}

				System.out.println("Please choose your seats e.g if you want to choose 2 seats as E0,E1"
						+ " press E0 Enter,E1 Enter ");
				String[] holdSeats = new String[noOfSeats];
				for (int i = 0; i < noOfSeats; i++) {
					holdSeats[i] = sc.next();

				}

				// hold seats for a customer
				seatheld = ticketService.bookSeats(holdSeats, email);
				System.out.println(SeatInfo.seats);
				System.out.println("Total number of Available seats: " + ticketService.numSeatsAvailable());
				System.out.println(noOfSeats + " Seats have been held for you.Do you want to confirm?(yes/no)"
						+ " Please confirm within 5 secs");
				String confirm = sc.next();

				// confirm within 5 secs
				if (confirm.equalsIgnoreCase("yes")) {
					long currTime = System.currentTimeMillis();

					if (currTime - seatheld.getSeatsHeldAt() < 5000) {
						String confNo = ticketService.reserveSeats(seatheld.getSeatholdId(), email);
						System.out.println("Your Seats are confirmed and your confirmation number is :" + confNo);
						System.out.println(SeatInfo.seats);
					} else {

						ticketService.reset(seatheld.seatsHeld);
						System.out.println(SeatInfo.seats);
						System.out.println("your session timed out,no seat held Press C to continue Q to quit");
						String userResponse = sc.next();
						if (userResponse.equalsIgnoreCase("C")) {
							continue;
						} else if (userResponse.equalsIgnoreCase("Q")) {
							System.out.println("Thanks for using Ticket Service!!");
							sc.close();
							System.exit(0);
						}
					}
				} else {
					ticketService.reset(seatheld.seatsHeld);
					System.out.println(SeatInfo.seats);
				}

				System.out.println("Total number of Available seats: " + ticketService.numSeatsAvailable());
				System.out.println("Press C-to continue booking more seats Q-to quit the application");

				String userResponse = sc.next();
				if (userResponse.length() > 1) {
					System.out.println("INVALID RESPOSE!! Please press a single Key from keyboard ");
					System.out.println("Press C-to continue booking more seats Q-to quit the application");
				} else {
					if (userResponse.equalsIgnoreCase("C")) {
						continue;
					} else if (userResponse.equalsIgnoreCase("Q")) {
						System.out.println("Thanks for using Ticket Service!!");
						sc.close();
						System.exit(0);
					}
				}
			}

		} catch (InputMismatchException e) {
			System.out.println("invalid input type");
		} catch (Exception e) {
			e.printStackTrace();
			throw new InterruptedException();
		}
	}

}
