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
}










