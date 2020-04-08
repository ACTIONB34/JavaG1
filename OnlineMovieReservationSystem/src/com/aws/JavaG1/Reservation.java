package com.aws.JavaG1;

import java.util.Date;

public class Reservation {

    private Timeslot timeslot;
    private Cinema cinema;
    private Date date;
    private String time;
    private String customer_name;
    private int reservationID;
    private int seatId;
    private int noOfChildrens;
    private int noOfAdults;
    private int noOfSeniors;
    private int totalAmount;

    public Reservation() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Reservation(Timeslot timeslot, Cinema cinema, int seatId, int noOfChildrens, int noOfAdults, int noOfSeniors, int totalAmount) {
        this.timeslot = timeslot;
        this.cinema = cinema;
        this.seatId = seatId;
        this.noOfChildrens = noOfChildrens;
        this.noOfAdults = noOfAdults;
        this.noOfSeniors = noOfSeniors;
        this.totalAmount = totalAmount;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
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
        this.updateTotalAmount();
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
        this.updateTotalAmount();

    }

    public int getNoOfSeniors() {
        return noOfSeniors;
    }

    public void setNoOfSeniors(int noOfSeniors) {
        this.noOfSeniors = noOfSeniors;
        this.updateTotalAmount();
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void updateTotalAmount() {
        this.totalAmount = (this.noOfAdults * 150) + (this.noOfChildrens * 100) + (this.noOfSeniors * 120);
    }

    public int getTotalPeople(){
    	return this.noOfAdults + this.noOfSeniors + this.noOfChildrens;
	}

	public Boolean isCinemaFull(){
    	return this.cinema.getNoOfSeats() <= getTotalPeople();
	}

	public Boolean isValidReservation(){
    	return this.cinema != null && this.timeslot != null &&  !isCinemaFull();
	}

    @Override
    public String toString() {
        String info = "";
        info += "Date: " + this.getDate().toString();
        info += "\nCinema: " + this.getCinema().getCinemaId();
        info += "\nMovie Title: " + this.getCinema().getMovie().getMovieName();
        info += "\nTimeslot #: " + this.getTimeslot().getTimeSlotID();
        info += "\nTotal persons: " +this.getTotalPeople();

        return info;
    }
}
