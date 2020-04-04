package com.aws.JavaG1;

import java.util.ArrayList;

/**
 * 
 */

/**
 * @author andre.maraton
 *
 */
public class Customer {
	
	private int customerID;
	private ArrayList<Reservation> reservations;
	private String customerName;

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void addReservation (Reservation reservation){
		if(this.reservations == null)
			this.reservations = new ArrayList<>();
		this.reservations.add(reservation);
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Customer(int customerID, String customerName){
		this.customerID = customerID;
		this.customerName = customerName;
	}

	public Customer(int customerID, String customerName, ArrayList<Reservation> reservations){
		this.customerID = customerID;
		this.customerName = customerName;
		setReservations(reservations);
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










