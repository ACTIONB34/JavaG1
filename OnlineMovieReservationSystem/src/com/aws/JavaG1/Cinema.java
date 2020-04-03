package com.aws.JavaG1; /**
 *
 */

import java.util.ArrayList;

/**
 * @author chanel.baluyos
 *
 */
public class Cinema {

    private int cinemaId;
    private Movie movie;
    private ArrayList<Timeslot> timeslots;
    private int noOfSeats;
    private String status;

    public Cinema() {
        this.noOfSeats = 40;
    }

    public Cinema(int cinemaId, Movie movie, ArrayList<Timeslot> timeslots, int noOfSeats, String status) {
        this.cinemaId = cinemaId;
        this.movie = movie;
        this.timeslots = timeslots;
        this.noOfSeats = noOfSeats;
        this.status = status;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void addTimeSlot(Timeslot timeslot) {
        if (this.timeslots == null){
            this.timeslots = new ArrayList<Timeslot>();
        }
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

    @Override
    public String toString() {
        String info = "\n==========================================\n";
				info += "Cinema " + this.getCinemaId() + "\n\n";
        info += this.getMovie().getMovieName() + "\n";
        info += this.getMovie().getMovieGenre() + "\n";
        info += this.getMovie().getMovieRating() + "\n";

        for (Timeslot timeslot : this.getTimeslots()) {
            info += "Show " + timeslot.getTimeSlotID() + ": " + timeslot.getTimeStart() + "\n";
        }

		info += "==========================================";
        return info;

    }
}
