package com.aws.JavaG1; /**
 * 
 */

/**
 * @author chanel.baluyos
 *
 */
public class Cinema {

	private Timeslot[] timeslots;
	private int noOfSeats;
	private String status;

	public Cinema(Timeslot[] timeslots, int noOfSeats, String status) {
		this.timeslots = timeslots;
		this.noOfSeats = noOfSeats;
		this.status = status;
	}

	public Timeslot[] getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(Timeslot[] timeslots) {
		this.timeslots = timeslots;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
