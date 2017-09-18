package com.ticketservice.ticketApp;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds the Seat informations in a static hash map and initializes
 * it.The hash map contains seatId as the key(of type String) and seat status as
 * value(of type enum)
 *
 */

public class SeatInfo {
	static Map<String, SeatType> seats;

	public SeatInfo() {
		if (seats == null) {
			seats = new HashMap<String, SeatType>();
		}
	}

	public enum SeatType {
		AVAILABLE("Available"), HELD("Held"), RESERVED("Reserved");
		private String seatStatus;

		private SeatType(String seatStatus) {
			this.seatStatus = seatStatus;
		}

		public String getseatStatus() {
			return seatStatus;
		}
	}

	public Map<String, SeatType> createSeats() {

		int row = 5, column = 5;
		char[] rowid = "ABCDE".toCharArray();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++)
				seats.put(Character.toString(rowid[i]) + j, SeatInfo.SeatType.AVAILABLE);
		}
		return seats;
	}
}