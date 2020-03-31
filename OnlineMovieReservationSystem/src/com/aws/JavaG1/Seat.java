package com.aws.JavaG1;

public class Seat {
    private int seatId;
    private Cinema cinema;
    private String status;

    public Seat() {
    }

    public Seat(int seatId, Cinema cinema, String status) {
        this.seatId = seatId;
        this.cinema = cinema;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
