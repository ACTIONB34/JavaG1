package com.aws.JavaG1; /**
 * 
 */

import java.util.ArrayList;

/**
 * @author chanel.baluyos
 *
 */
public class Cinema {

	private Movie movies;
	private ArrayList<Timeslot> timeslots;
	private int noOfSeats;
	private String status;

	public Cinema(Movie movies, ArrayList<Timeslot> timeslots, int noOfSeats, String status) {
		this.movies = movies;
		this.timeslots = timeslots;
		this.noOfSeats = noOfSeats;
		this.status = status;
	}

	public Movie getMovies() {
		return movies;
	}

	public void setMovies(Movie movies) {
		this.movies = movies;
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
