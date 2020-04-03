package com.aws.JavaG1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author andre.maraton
 *
 */
public class Customer {
	
	private int customerID;
	private Reservation reservations;
	private String customerName;

	public Reservation getReservation() {
		return reservations;
	}

	public void setReservation(Reservation reservation) {
		this.reservations = reservation;
	}
	
	public Customer(int customerID, String customerName){
		this.customerID = customerID;
		this.customerName = customerName;
	}

	public Customer(int customerID, String customerName, Reservation reservation){
		this.customerID = customerID;
		this.customerName = customerName;
		setReservation(reservation);
	}
	
	public int getCustomerID(){
		return customerID;
	}
	
	public void setCustmerID(int customerID){
		this.customerID = customerID;
	}
	
	public String getCustomerName(){
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public void viewMovies(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");

		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("select * from movie;");
		    
		    while(rs.next()) {
		    	System.out.println(rs.getString(2));
		    }
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public void reserveTicket(){
		int cinemaID = 1;
		String time = "10:00 AM - 12:00 PM, Nov-20-2020";
		int customerID = 1;
		double totalPayment = 1150.00;
		int noOfKids = 5;
		int noOfAdults = 5;
		int noOfSeniors = 5;
		
		try {
			Connection conn = null;
			Statement stmt = null;
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/pet_clinic?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");
			
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO `reservation`(cinemaID,time,customerID,totalPayment,noOfKids,noOfAdults,noOfSeniors) "
		    		+ "VALUE ('"+cinemaID+"','"+time+"','"+customerID+"',"+totalPayment+",'"+noOfKids+"','"+noOfAdults+"','"+noOfSeniors+"')");

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}










