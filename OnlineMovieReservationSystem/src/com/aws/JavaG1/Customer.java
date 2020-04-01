package com.aws.JavaG1;

import java.sql.Connection;
import java.sql.DriverManager;
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
	private Reservation reservation;
	private String customerName;

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
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
		    rs = stmt.executeQuery("select * from movie.movies;");
		    
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
	
	public void reserveTicket(int cinema_id, String time, int customer_id, double total_payment,int noOfKids, int noOfAdults, int noOfSeniors){
		//create database connection
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//open database 
		    conn = DriverManager.getConnection("jdbc:mysql://localhost/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");
		    
		    //Saving data into the database
		    stmt = conn.createStatement();
		    stmt.executeUpdate("INSERT INTO reservation (cinema_id,time,customer_id,total_payment, no_of_kid, no_of_adult, no_of_senior)"
		            +"VALUES (cinema_id, time, customer_id,total_payment, noOfKids, noOfAdults, noOfSeniors)");

		    //close database
		    conn.close();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public static void main(String[] args){
		
	}
}
