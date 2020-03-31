/**
 * 
 */

/**
 * @author candace.madelo
 *
 */
public class Reservations {
	
	int reservationID;
	int totalAmount;
	int seatID;
	int cinemaID;
	int customerID;
	
	
	public Reservations(int reservationID, int totalAmount, int seatID, int cinemaID, int customerID) {
		this.reservationID = reservationID;
		this.totalAmount = totalAmount;
		this.seatID = seatID;
		this.cinemaID = cinemaID;
		this.customerID = customerID;
	}


	public int getReservationID() {
		return reservationID;
	}


	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}


	public int getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}


	public int getSeatID() {
		return seatID;
	}


	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}


	public int getCinemaID() {
		return cinemaID;
	}

	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public void updateReservation() {
		
	}

}
