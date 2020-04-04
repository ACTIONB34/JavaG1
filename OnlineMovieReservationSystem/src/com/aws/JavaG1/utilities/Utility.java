package com.aws.JavaG1.utilities;

import com.aws.JavaG1.Cinema;
import com.aws.JavaG1.Movie;
import com.aws.JavaG1.Timeslot;

import java.util.ArrayList;

public class Utility {


    public static void populateCinema(ArrayList<Cinema> cinemas, ArrayList<Timeslot> timeslots, ArrayList<Movie> movies){

        for(Timeslot timeslot: timeslots){
            int cinemaID = timeslot.getCinemaID();
            int movieID = timeslot.getMovieID();

            Cinema cinema = getCinemaByID(cinemas, cinemaID);
            cinema.setMovie(getMovieByID(movies,movieID));
            cinema.addTimeSlot(timeslot);
        }

    }

    public static Cinema getCinemaByID(ArrayList<Cinema> cinemas, int cinemaID){
        for(Cinema cinema: cinemas){
            if(cinema.getCinemaId() == cinemaID)
                return cinema;
        }
        return null;
    }
    public static Movie getMovieByID(ArrayList<Movie> movies, int movieID){
        for(Movie movie: movies){
            if(movie.getMovieID() == movieID)
                return movie;
        }
        return null;
    }

    public static Timeslot getTimeSlotById(ArrayList<Timeslot> timeslots, int timeslotID){
        for(Timeslot timeslot: timeslots){
            if(timeslot.getTimeSlotID() == timeslotID)
                return timeslot;
        }
        return null;
    }
}
