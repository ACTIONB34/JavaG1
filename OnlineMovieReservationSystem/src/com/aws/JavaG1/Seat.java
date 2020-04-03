package com.aws.JavaG1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Seat {
    private static String number;
	private int seatId;
    private Cinema cinema;
    private boolean status; // 0-free, 1-occupied
    Object[] obj;
    
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
    
	
	public static void viewSeats(int cinema_id, int timeslot_id, int numOfPeople){
		Object[] obj  = new Object[numOfPeople];
		
			int seat;
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
			    conn =
			       DriverManager.getConnection("jdbc:mysql://localhost/moviereservation?"
			       		+ "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			    		   "root", "awsys+123");
			    stmt = conn.createStatement();
			    rs = stmt.executeQuery("SELECT * FROM seats WHERE cinema_id ="+cinema_id+"&& timeslot_id ="+timeslot_id+";");   
			    System.out.println("Seat Selection Info");
			    System.out.println("\n\nPlease choose your seats from the available seats below: ");
			    int i = 0;
			    while(rs.next()) {
			    	if(i < 7){
			    		if(rs.getInt(4) == 0)
				    	{
				    		System.out.print(rs.getInt(1)+"\t");
				    	}
				    	else{
				    		System.out.print("-\t");
				    	}
			    		i++;
			    	}
			    	else{
			    		if(rs.getInt(4) == 0)
				    	{
				    		System.out.println(rs.getInt(1));
				    	}
				    	else{
				    		System.out.println("-");
				    	}
			    		
			    		i = 0;
			    	}
			    }
			    
				for(int j = 0; j < numOfPeople; j++){
					
					Scanner input = new Scanner(System.in);
					System.out.print(" Choose seat:");
					seat = input.nextInt();
					obj[j] = seat;
				}
				for(int k = 0; k < numOfPeople; k++){
					System.out.println(obj[k]);
					int seatNumber = (Integer) obj[k];
					updateSeat(seatNumber);
					
				}	
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		
	}
	
	public static void updateSeat(int seat_id){
		DatabaseConnect.DatabaseConnect();
		try {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/moviereservation?"
		       		+ "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT MAX(reservation_id) FROM reservations"); 
		    
		    while(rs.next()){
		    	int lastReservedID = rs.getInt(1)+1;
			    stmt = conn.createStatement();
			    String query1 = "UPDATE seats SET seats.reservation_id = "+lastReservedID+" WHERE seat_id ="+seat_id;
		        stmt.executeUpdate(query1);
		    }
		    
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public static void viewCinema(){
		int userInput;
		
		Scanner input = new Scanner(System.in);
	    System.out.println();
		System.out.println("Select Cinema: ");
		userInput = input.nextInt();
		
		int cinemaID = userInput;
		
		viewTimeslot(cinemaID);
	}
	
	public static void viewTimeslot(int cinemaID){
		
		int timeslotID;
		Scanner input = new Scanner(System.in);
		System.out.println("Select Time: ");
		timeslotID = input.nextInt();
		
		numOfPeople(cinemaID, timeslotID);	
	}
	
	public static void numOfPeople(int cinemaID, int timeSlotID){
		int numOfPeople;
		Scanner input = new Scanner(System.in);
		System.out.println("Input number of People: ");
		numOfPeople = input.nextInt();
		
		viewSeats(cinemaID, timeSlotID, numOfPeople);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		viewCinema();
		
	}
}
