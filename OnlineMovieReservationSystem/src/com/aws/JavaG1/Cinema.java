package com.aws.JavaG1; /**
 * 
 */

/**
 * @author chanel.baluyos
 *
 */
public class Cinema {

	private Movie[] movies;
	private int noOfSeats;
	private String status;

	public Cinema(Movie[] movies, int noOfSeats, String status) {
		this.movies = movies;
		this.noOfSeats = noOfSeats;
		this.status = status;
	}

	public Movie[] getMovies() {
		return movies;
	}

	public void setMovies(Movie[] movies) {
		this.movies = movies;
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
