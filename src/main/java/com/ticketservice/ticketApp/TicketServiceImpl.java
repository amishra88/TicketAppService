package com.ticketservice.ticketApp;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import com.ticketservice.ticketApp.SeatInfo.SeatType;

public class TicketServiceImpl implements TicketService {

	SeatHold sh = new SeatHold();
	static long startTime;

	/**
	 * Implements Interface method,calls the common method for available seats and
	 * count how many seats are available at this point of time
	 */

	public int numSeatsAvailable() throws Exception {
		String[] availSeats1 = availableSeats();
		int blank = 0;
		for (String s : availSeats1) {
			if (s == null) {
				blank++;
			}
		}

		return availSeats1.length - blank;
	}

	/**
	 * Implements Interface method,assigns values Seathold object created for that
	 * customer .
	 */

	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) throws Exception {

		sh.email = customerEmail;
		sh.seatholdId = generateRandomInteger();
		sh.confirmNo = null;
		return sh;
	}

	/**
	 * Implements Interface method,reserve Seats for a customer that are held ,if
	 * the customer confirms it within 5 secs.
	 * 
	 */

	public String reserveSeats(int seatHoldId, String customerEmail) throws Exception {
		if (sh.seatholdId == seatHoldId && sh.email == customerEmail) {
			String[] reserveSeats = sh.getSeatHeld();
			if (reserveSeats.length > 0) {
				for (String i : reserveSeats) {

					SeatInfo.seats.put(i, SeatInfo.SeatType.RESERVED);
				}
				sh.setConfirmNo(Integer.toString(sh.seatholdId) + "confirmed");
				sh.seatholdId = 0;

			}
		}
		return sh.getConfirmNo();
	}

	/** 
	 * Returns String array containing the seatIds of available seats at any point
	 * of time 
	 */
	public String[] availableSeats() {

		Iterator it = SeatInfo.seats.entrySet().iterator();
		int count = 0;
		String[] seatIds = new String[25];
		while (it.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<String, SeatType> pair = (Map.Entry<String, SeatType>) it.next();
			if (pair.getValue() == SeatInfo.SeatType.AVAILABLE) {
				seatIds[count] = pair.getKey();
				count++;
			}
		}
		return seatIds;

	}

	/**  
	 *  Holds seats for a customer that customer choose and call findAndHoldSeats
	 *  method to load SeatHold
	 *  object with given customer info and use the object returned.
	 *  The seatId that is going to be held is synchronized,so that one thread
	 *  can access same seat to hold at same time
     */
	public SeatHold bookSeats(String[] seatsHold, String email) {
		String[] availSeats = availableSeats();
		int numSeats = seatsHold.length;
		if (availSeats.length >= numSeats) {

			for (String i : seatsHold) {
				if (Arrays.asList(availSeats).contains(i)) {
					synchronized (i) {
						SeatInfo.seats.put(i, SeatInfo.SeatType.HELD);
						startTime = System.currentTimeMillis();
					}
				}else {
					System.out.println("Seat "+i+" is not available.Choose again");
					
					System.exit(0);
				}

			}
			try {
				sh = findAndHoldSeats(numSeats, email);
			} catch (Exception e) {

				e.printStackTrace();
			}
			sh.seatsHeld = seatsHold;
			sh.setSeatsHeldAt(startTime);
		}else System.out.println(numSeats+" not available");
		return sh;
	}

	/** 
	 * Resets the seats status to available,if the customer doesn't confirm it or
	 * doesn't confirm it within 3 secs
     */
	public void reset(String[] seatsHeld) {
		for (String s : seatsHeld) {
			SeatInfo.seats.put(s, SeatType.AVAILABLE);
		}
	}

	/** 
	 * generates a random number
     */
	public static int generateRandomInteger() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(1000000);
		String formatted = String.format("%06d", num);
		return Integer.parseInt(formatted);
	}

}
