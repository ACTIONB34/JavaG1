package com.aws.JavaG1; /**
 *
 */

/**
 * @author candace.madelo
 *
 */
public class Reservation {

    private Timeslot timeslot;
    private Cinema cinema;
    private int seatId;
    private int noOfChildrens;
    private int noOfAdults;
    private int noOfSeniors;
    private int totalAmount;

    public Reservation() {
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
		return "Reservation{" +
				"timeslot=" + timeslot +
				", cinema=" + cinema +
				", seatId=" + seatId +
				", noOfChildrens=" + noOfChildrens +
				", noOfAdults=" + noOfAdults +
				", noOfSeniors=" + noOfSeniors +
				", totalAmount=" + totalAmount +
				'}';
	}
}
