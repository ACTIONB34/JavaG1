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
	private Reservation reservations;
	private String customerName;

	public Reservation getReservations() {
		return reservations;
	}

	public void setReservations(Reservation reservation) {
		this.reservations = reservation;
	}


	
	public Customer(int customerID, String customerName){
		this.customerID = customerID;
		this.customerName = customerName;
	}

	public Customer(int customerID, String customerName, Reservation reservation){
		this.customerID = customerID;
		this.customerName = customerName;
		setReservations(reservation);
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
		    rs = stmt.executeQuery("select * from movie.customers;");
		    
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
	
	public void addCustomer(){
		
	}
	
	public void reserveTicket(){
		String custName;
		
		Scanner input = new	Scanner(System.in);
		System.out.print("N: ");
		System.out.println("");
		custName = input.next();
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/movie?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");

		    stmt = conn.createStatement();
		    stmt.executeUpdate("INSERT INTO customer (customerName)"
		            +"VALUES (custName)");

		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public void checkOut(){
		
	}
}
