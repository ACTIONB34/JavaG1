package com.aws.JavaG1;

import com.aws.JavaG1.utilities.Utility;

import java.sql.*;
import java.util.*;

public class DatabaseConnect {
	
	private static Connection connect;
	private Statement statement;
	private static ResultSet result;
	private static Connection connect2;
	private static ResultSet result2;

	private static String SELECT_MOVIES = "SELECT " + 
			"cinema_id, movie_name, movie_director, movie_rating,movie_genre " +
			"FROM movies, cinemas WHERE movies.movie_id = cinemas.movie_id " + 
			"AND movies.status = 1;";

	private static String SELECT_UNRESERVED_SEATS = "SELECT " +  
		    "seat_number " +
			"FROM seats WHERE timeslot_id = ? AND cinema_id = ? " +
		    "AND reservation_id IS NULL;";
	
	private static String SELECT_RESERVED_SEATS = "SELECT " +  
		    "seat_number " +
			"FROM seats WHERE cinema_id = ? AND timeslot_id = ? " +
		    "AND reservation_id IS NOT NULL;";


	private static String UPDATE_SEATS = "UPDATE seats " +  
		    "SET reservation_id = ? " +
			"WHERE cinema_id = ? and timeslot_id = ? and seat_number = ?;";
	
	private static String SELECT_RESERVATION_ID = "SELECT " +
			"MAX(reservation_id) as reservation_id from reservations";
	
	private String ADDTO_DB = "INSERT INTO reservations (reservation_id, date, cinema_id, time, customer_name, total_payment, no_of_kid, no_of_adult, no_of_senior, timeslot_id)"
							+ " VALUES (null, current_timestamp, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static String genericPassword = "awsys+123";
	public DatabaseConnect() {
		this.connect = null;
		this.statement = null;
		this.result = null;
		
		try {
		    connect = DriverManager.getConnection("jdbc:mysql://localhost/moviereservation?useUnicode=true&"
		    		+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", genericPassword);
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	public static void init(){
		connect2 = null;
		result2 = null;

		try {
			connect2 = DriverManager.getConnection("jdbc:mysql://localhost/moviereservation?useUnicode=true&"
							+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", genericPassword);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public static void closeConnection(){
		try {
			connect2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Reservation> getAllReservationByName(String name, ArrayList<Timeslot> timeslots, ArrayList<Cinema> cinemas){
		init();
		String query = "SELECT * FROM moviereservation.reservations where customer_name = \""+name+"\";";
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();

		try {
			PreparedStatement ps = connect2.prepareStatement(query);
			result2 = ps.executeQuery();

			while(result2.next()){
				Reservation reservation = new Reservation();
				reservation.setReservationID(result2.getInt("reservation_id"));
				reservation.setTimeslot(Utility.getTimeSlotById(timeslots, result2.getInt("timeslot_id")));
				reservation.setCinema(Utility.getCinemaByID(cinemas, result2.getInt("cinema_id")));
				reservation.setDate(result2.getDate("date"));
				reservation.setTime(result2.getString("time"));
				reservation.setCustomer_name(result2.getString("customer_name"));
				reservation.setTotalAmount(result2.getInt("total_payment"));
				reservation.setNoOfChildrens(result2.getInt("no_of_kid"));
				reservation.setNoOfAdults(result2.getInt("no_of_adult"));
				reservation.setNoOfSeniors(result2.getInt("no_of_senior"));
				reservations.add(reservation);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}

		return reservations;

	}

	public static ArrayList<Cinema> getAllCinemas(){
		init();
		String query = "SELECT * FROM moviereservation.cinemas;";
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();

		try {
			PreparedStatement ps = connect2.prepareStatement(query);
			result2 = ps.executeQuery();
			
			while(result2.next()){
				Cinema cinema = new Cinema();
				cinema.setCinemaId(result2.getInt("cinema_id"));
				cinemas.add(cinema);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
		return cinemas;

	}

	public static ArrayList<Timeslot> getAllTimeSlots(){
		init();
		String query = "SELECT * FROM moviereservation.timeslots;";
		ArrayList<Timeslot> timeslots = new ArrayList<Timeslot>();

		try {
			PreparedStatement ps = connect2.prepareStatement(query);
			result2 = ps.executeQuery();

			while(result2.next()){
				Timeslot timeslot = new Timeslot();
				timeslot.setTimeSlotID(result2.getInt("timeslot_id"));
				timeslot.setTimeStart(result2.getString("time_start"));
				timeslot.setMovieID(result2.getInt("movie_id"));
				timeslot.setCinemaID(result2.getInt("cinema_id"));
				timeslots.add(timeslot);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
		
		return timeslots;
	}


	public static ArrayList<Movie> getAllMovies(){
		init();
		String query = "SELECT * FROM moviereservation.movies;";
		ArrayList<Movie> movies = new ArrayList<Movie>();

		try {
			PreparedStatement ps = connect2.prepareStatement(query);
			result2 = ps.executeQuery();

			while(result2.next()){
				Movie movie = new Movie();
				movie.setMovieID(result2.getInt("movie_id"));
				movie.setMovieName(result2.getString("movie_name"));
				movie.setMovieDirector(result2.getString("movie_director"));
				movie.setMovieGenre(result2.getString("movie_genre"));
				movie.setMovieRating(result2.getString("movie_rating"));
				movies.add(movie);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}

		return movies;

	}
	
	
	public static ResultSet selectMovies(){
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_MOVIES);
			result = ps.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();
			int column = rsmd.getColumnCount();
			
			for (int i = 1; i <= column; i++ ) {
				 String name = rsmd.getColumnName(i);
				 System.out.print(name + "\t");
			}
	    	System.out.println();
			
	    	while(result.next()) {
				Movie movie = new Movie(result.getInt("cinema_id"),
										result.getString("movie_name"),
										result.getString("movie_director"),
										result.getString("movie_rating"),
										result.getString("movie_genre"));
		    	
				System.out.println(movie.getMovieID()+ "\t"
								 + movie.getMovieName() + "\t"
								 + movie.getMovieDirector() + "\t"
								 + movie.getMovieRating() + "\t"
								 + movie.getMovieGenre());
		    }
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return result;
		
	}
	
	public static ResultSet selectTimeslots(){
		
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_MOVIES);
			result = ps.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();
			int column = rsmd.getColumnCount();
			
			for (int i = 1; i <= column; i++ ) {
				 String name = rsmd.getColumnName(i);
				 System.out.print(name + "\t");
			}
			
	    	System.out.println();
			
	    	while(result.next()) {
	    		System.out.print(result.getString("movie_id") + "\t"
				    +    result.getString("time_start"));
		    	System.out.println();
		    }
	    	
		}catch (SQLException e){
			e.printStackTrace();
		}	
		
		return result;
	}
	
	public static ResultSet selectSeats(){
		
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_UNRESERVED_SEATS);
			result = ps.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();
			int column = rsmd.getColumnCount();
			
			for (int i = 1; i <= column; i++ ) {
				 String name = rsmd.getColumnName(i);
				 System.out.print(name + "\t");
			}
			
	    	System.out.println();
			
	    	while(result.next()) {
	    		System.out.print(result.getString("seat_number"));
		    	System.out.println();
		    }
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return result;
	}
	
	public static int updateSeats(int reservation_id, Reservation r, List<Integer> numberOfSeats){
		int res = -1;
		try {
			PreparedStatement ps = connect.prepareStatement(UPDATE_SEATS);
			ps.setInt(1,reservation_id);
			ps.setInt(2,r.getCinema().getCinemaId());
			ps.setInt(3,r.getTimeslot().getTimeSlotID());

			for (int i = 0; i < numberOfSeats.size(); i++) {
				ps.setInt(4,numberOfSeats.get(i));
				ps.executeUpdate();
				res = 1;
			}	    	
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return res;
	}
	
	public static int selectReservationId(){
		int id = -1;
		
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_RESERVATION_ID);
			result = ps.executeQuery();

			if(result.next()) {
				id = result.getInt("reservation_id");
			}
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return id;
	}
	
	public void confirmReservation(Reservation r, Customer c){
		
		try{
			PreparedStatement ps = connect.prepareStatement(ADDTO_DB);
			ps.setInt(1, r.getCinema().getCinemaId());
			ps.setString(2, r.getTimeslot().getTimeStart());
			ps.setString(3, c.getCustomerName());
			ps.setInt(4, r.getTotalAmount());
			ps.setInt(5, r.getNoOfChildrens());
			ps.setInt(6, r.getNoOfAdults());
			ps.setInt(7, r.getNoOfSeniors());
			ps.setInt(8, r.getTimeslot().getTimeSlotID());

			ps.executeUpdate();
			
			ps.close();
			connect.close();

		}catch(SQLException e){
			e.printStackTrace();
		}

	}
	
 	
	public void viewSeats(int cinema_id, int timeslot_id){
		int columns = 7;
		int reservation_id = 4;
		int seat_number = 2;
		try {
			Statement stmt = connect.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT * FROM seats WHERE cinema_id ="+cinema_id+" && timeslot_id ="+timeslot_id+";");   
		    
		    if(!(rs.equals(null))){
		    	int i = 0;
		    	System.out.println("Seat Selection Info");
			    System.out.println("\n\nPlease choose your seats from the available seats below: ");
		    	while(rs.next()) {
			    	if(i < columns){
			    		if(rs.getInt(reservation_id) == 0)
				    	{
				    		System.out.print(rs.getInt(seat_number)+"\t");
				    	} else {
				    		System.out.print("-\t");
				    	}
			    		i++;
			    	} else {
			    		if(rs.getInt(reservation_id) == 0)
				    	{
				    		System.out.println(rs.getInt(seat_number));
				    	} else {
				    		System.out.println("-");
				    	}
			    		i = 0;
			    	}
			    }
		    }else {
		    	System.out.println("Sorry! No available seats.");
		    }

		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			closeConnection();
		}
	}
	
	public ArrayList<Integer> reservedSeats(int cinema_id, int timeslot_id){
		ArrayList<Integer> reserved = new ArrayList<Integer>();
		
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_RESERVED_SEATS);
			ps.setInt(1,cinema_id);
			ps.setInt(2,timeslot_id);
			result = ps.executeQuery();

			while(result.next()){
				reserved.add(result.getInt("seat_number"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return reserved;

	}

}
