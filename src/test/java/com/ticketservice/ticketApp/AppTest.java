package com.ticketservice.ticketApp;

import java.util.concurrent.TimeUnit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for TicketService App
 */
public class AppTest extends TestCase {

	/**
	 * Test for ticket service Happy Path
	 */
	public void testTicketServiceHappyPath() {
		SeatInfo seats1 = new SeatInfo();
		seats1.createSeats();
		TicketServiceImpl ticketService = new TicketServiceImpl();
		String[] seats = { "E0", "E1" };
		SeatHold seatheld = ticketService.bookSeats(seats, "dummyemail@gmail.com");
		assertNotNull(seatheld);
		assertEquals(SeatInfo.seats.get("E0").getseatStatus(), "Held");
		assertEquals(SeatInfo.seats.get("E1").getseatStatus(), "Held");

		String confNo = null;
		try {
			confNo = ticketService.reserveSeats(seatheld.getSeatholdId(), "dummyemail@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(confNo);
		assertEquals(seatheld.confirmNo, confNo);
		assertEquals(SeatInfo.seats.get("E0").getseatStatus(), "Reserved");
		assertEquals(SeatInfo.seats.get("E1").getseatStatus(), "Reserved");
	}

	/**
	 * Test for Error Scenario when the time waited is more than 5 secs
	 */
	public void testTicketServiceErrorScenario() {

		SeatInfo seats1 = new SeatInfo();
		seats1.createSeats();
		TicketServiceImpl ticketService = new TicketServiceImpl();
		String[] seats = { "E0", "E1" };
		SeatHold seatheld = ticketService.bookSeats(seats, "dummyemail@gmail.com");
		assertNotNull(seatheld);
		assertEquals(SeatInfo.seats.get("E0").getseatStatus(), "Held");
		assertEquals(SeatInfo.seats.get("E1").getseatStatus(), "Held");
		try {
			TimeUnit.SECONDS.sleep(7); // Adding a wait for 7 secs
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(seatheld.confirmNo, null);
	}

	/**
	 * Test for Reset method where the seats are reset to Available from Held
	 */
	public void testTicketServiceResetSeatStatus() {
		SeatInfo seats1 = new SeatInfo();
		seats1.createSeats();
		TicketServiceImpl ticketService = new TicketServiceImpl();
		String[] seats = { "E0", "E1" };
		SeatHold seatheld = ticketService.bookSeats(seats, "dummyemail@gmail.com");
		assertNotNull(seatheld);
		assertEquals(SeatInfo.seats.get("E0").getseatStatus(), "Held");
		assertEquals(SeatInfo.seats.get("E1").getseatStatus(), "Held");
		ticketService.reset(seatheld.seatsHeld);
		assertEquals(SeatInfo.seats.get("E0").getseatStatus(), "Available");
		assertEquals(SeatInfo.seats.get("E1").getseatStatus(), "Available");
	}
}
