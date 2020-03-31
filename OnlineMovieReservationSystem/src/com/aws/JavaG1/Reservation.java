package com.aws.JavaG1; /**
 * 
 */

/**
 * @author candace.madelo
 *
 */
public class Reservation {
	
	private Reservation reservation;
	private Cinema cinema;
	private int seatId;
	private int noOfChildrens;
	private int noOfAdults;
	private int noOfSeniors;
	private int totalAmount;

	public Reservation() {
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getNoOfChildrens() {
		return noOfChildrens;
	}

	public void setNoOfChildrens(int noOfChildrens) {
		this.noOfChildrens = noOfChildrens;
	}

	public int getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public int getNoOfSeniors() {
		return noOfSeniors;
	}

	public void setNoOfSeniors(int noOfSeniors) {
		this.noOfSeniors = noOfSeniors;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}



}
