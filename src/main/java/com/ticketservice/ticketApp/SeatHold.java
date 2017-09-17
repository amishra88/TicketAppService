package com.ticketservice.ticketApp;

/**
 * This class holds customer info,the array of seatIds customer wants to hold
 * and when he holds it,a seatholdId is generated when the seats are held.If
 * customer confirms the seats ,a confirmation number is generated.
 *
 */

public class SeatHold {

	String email;
	int seatholdId;
	String confirmNo;
	String[] seatsHeld;
	long seatsHeldAt;

	public long getSeatsHeldAt() {
		return seatsHeldAt;
	}

	public void setSeatsHeldAt(long seatsHeldAt) {
		this.seatsHeldAt = seatsHeldAt;
	}

	public String[] getSeatHeld() {
		return seatsHeld;
	}

	public void setSeatHeld(String[] seatHeld) {
		this.seatsHeld = seatHeld;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSeatholdId() {
		return seatholdId;
	}

	public void setSeatholdId(int seatholdId) {
		this.seatholdId = seatholdId;
	}

	public String getConfirmNo() {
		return confirmNo;
	}

	public void setConfirmNo(String confirmNo) {
		this.confirmNo = confirmNo;
	}

}
