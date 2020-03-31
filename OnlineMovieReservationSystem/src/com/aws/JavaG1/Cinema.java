package com.aws.JavaG1; /**
 * 
 */

import java.util.ArrayList;

/**
 * @author chanel.baluyos
 *
 */
public class Cinema {

	private Movie movie;
	private ArrayList<Timeslot> timeslots;
	private int noOfSeats;
	private String status;

	public Cinema(Movie movie, ArrayList<Timeslot> timeslots, int noOfSeats, String status) {
		this.movie = movie;
		this.timeslots = timeslots;
		this.noOfSeats = noOfSeats;
		this.status = status;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void addTimeSlot(Timeslot timeslot){
		if(this.timeslots == null)
			this.timeslots = new ArrayList<Timeslot>();
		this.timeslots.add(timeslot);
	}

	public ArrayList<Timeslot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(ArrayList<Timeslot> timeslots) {
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
