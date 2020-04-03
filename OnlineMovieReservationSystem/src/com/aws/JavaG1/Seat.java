package com.aws.JavaG1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
    

//	public static void viewSeats(int cinema_id, int timeslot_id){
//		int seat;
//	    
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//		    conn =
//		       DriverManager.getConnection("jdbc:mysql://localhost/moviereservation?"
//		       		+ "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
//		    		   "root", "awsys+123");
//		    stmt = conn.createStatement();
//		    rs = stmt.executeQuery("SELECT * FROM seats WHERE cinema_id ="+cinema_id+" && timeslot_id ="+timeslot_id+";");   
//		    System.out.println("Seat Selection Info");
//		    System.out.println("\n\nPlease choose your seats from the available seats below: ");
//		    int i = 0;
//		    while(rs.next()) {
//		    	if(i < 7){
//		    		if(rs.getInt(4) == 0)
//			    	{
//			    		System.out.print(rs.getInt(1)+"\t");
//			    	}
//			    	else{
//			    		System.out.print("-\t");
//			    	}
//		    		i++;
//		    	}
//		    	else{
//		    		if(rs.getInt(4) == 0)
//			    	{
//			    		System.out.println(rs.getInt(1));
//			    	}
//			    	else{
//			    		System.out.println("-");
//			    	}
//		    		
//		    		i = 0;
//		    	}
//		    }
//		    
//
//		    
//		
//		} catch (SQLException ex) {
//		    // handle any errors
//		    System.out.println("SQLException: " + ex.getMessage());
//		    System.out.println("SQLState: " + ex.getSQLState());
//		    System.out.println("VendorError: " + ex.getErrorCode());
//		}
//	}
//	
//	public static void viewCinema(){
//		int userInput;
//		
//		Scanner input = new Scanner(System.in);
//	    System.out.println();
//		System.out.println("Select Cinema: ");
//		userInput = input.nextInt();
//		
//		int cinemaID = userInput;
//		
//		viewTimeslot(cinemaID);
//	}
//	
//	public static void viewTimeslot(int cinemaID){
//		
//		int timeslotID;
//		Scanner input = new Scanner(System.in);
//		System.out.println("Select Time: ");
//		timeslotID = input.nextInt();
//		
//		viewSeats(cinemaID, timeslotID);
//		
//		
//	}
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException{
//		viewCinema();
//		
//	}
}
