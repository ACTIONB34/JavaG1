package com.aws.JavaG1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Timeslot {
	private int timeSlotID;
	private String timeStart;
	private int movieID;
	private int cinemaID;
	private int timeSLotID;

	public Timeslot() {
		
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public int getCinemaID() {
		return cinemaID;
	}

	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}



	public Timeslot(int timeSlotID, String timeStart) {
		this.timeSlotID = timeSlotID;
		this.timeStart = timeStart;
	}

	public int getTimeSlotID() {
		return timeSlotID;
	}

	public void setTimeSlotID(int timeSlotID) {
		this.timeSlotID = timeSlotID;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	@Override
	public String toString() {
		return "Timeslot{" +
				"timeSlotID=" + timeSlotID +
				", timeStart='" + timeStart + '\'' +
				", movieID=" + movieID +
				", cinemaID=" + cinemaID +
				'}';
	}
	
	public static void viewTimeslot(){
		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet ts = null;
		
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/moviereservation?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");
		    stmt = conn.createStatement();
		    ts = stmt.executeQuery("SELECT * FROM timeslots WHERE movie_id ="+1+";");   
		     
		    
		    for(int i = 1; i < 5; i++){
		    	
		    }
		    while(ts.next()) {
//		    	mv = stmt.executeQuery("SELECT * FROM movies WHERE movie_id ="+ts.getInt(4)+";");
		    	
		    	
		    	if(ts.getInt(1) == 1){
		    		System.out.println("1st Show: "+ts.getString(2));
//		    		System.out.println("Title: "+mv.getString(2));
		    	}
		    	else if(ts.getInt(1) == 2){
		    		System.out.println("2nd Show: "+ts.getString(2));
//		    		System.out.println("Title: "+mv.getString(2));
		    	}
		    	else{
		    		System.out.println("3rd Show: "+ts.getString(2));
//		    		System.out.println("Title: "+mv.getString(2));
		    	}
		    	
		    }
		
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		viewTimeslot();
	}
}
