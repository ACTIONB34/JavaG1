package com.aws.JavaG1;

public class Seat {
    private int seatId;
    private Cinema cinema;
    private boolean status; // 0-free, 1-occupied
    
    public Seat() {
    }

    public Seat(int seatId, Cinema cinema, boolean status) {
        this.seatId = seatId;
        this.cinema = cinema;
        this.status = status;
    }

    public Seat(int seatId){
    	this.seatId = seatId;
    	this.status = true;
    }
    
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
