package com.aws.JavaG1.utilities;

import com.aws.JavaG1.Movie;
import com.aws.JavaG1.Timeslot;

import java.util.ArrayList;

public class Utility {

    public static Movie getMovieByID(ArrayList<Movie> movies, int movieID){
        for(Movie movie: movies){
            if(movie.getMovieID() == movieID)
                return movie;
        }
        return null;
    }

    public static Timeslot getTimeSlotById(ArrayList<Timeslot> timeslots, int timeslotID){
        for(Timeslot timeslot: timeslots){
            if(timeslot.getMovieID() == timeslotID)
                return timeslot;
        }
        return null;
    }
}
