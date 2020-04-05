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
	
	private static String SELECT_TIMESLOTS = "SELECT " +  
		    "movies.movie_id, time_start" +
		    "FROM movies, timeslots where movies.movie_id = timeslots.movie_id;";

	private static String SELECT_UNRESERVED_SEATS = "SELECT " +  
		    "seat_number" +
			"FROM seats WHERE timeslot_id = ? AND cinema_id = ?" +
		    "AND reservation_id IS NULL;";

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
		    //System.out.println("CONN SUCCESS");
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
//			System.out.println("CONN SUCCESS");
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
			ResultSetMetaData rsmd = result2.getMetaData();

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

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
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
			ResultSetMetaData rsmd = result2.getMetaData();

			while(result2.next()){
				Cinema cinema = new Cinema();
				cinema.setCinemaId(result2.getInt("cinema_id"));
				cinemas.add(cinema);
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
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
			ResultSetMetaData rsmd = result2.getMetaData();

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
			// TODO Auto-generated catch block
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
			ResultSetMetaData rsmd = result2.getMetaData();

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
			// TODO Auto-generated catch block
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
//	    		System.out.print(result.getString("cinema_id") + "\t"
//				    +    result.getString("movie_name") + "\t"
//				    +    result.getString("movie_director") + "\t"
//				    +    result.getString("movie_rating") + "\t"
//				    +    result.getString("movie_genre"));
//		    	System.out.println();
		    	
		    	
		    	//String movie = result.getString("movie_name");
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
			// TODO Auto-generated catch block
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
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
	}
	
	//needs seat
	public static int updateSeats(int reservation_id, Reservation r, List<Integer> numberOfSeats){
		int res = -1;
		try {
			PreparedStatement ps = connect.prepareStatement(UPDATE_SEATS);
			//dummy
			//reservation
			//cinema_id = 1;
			//timeslot_id = 1;

			ps.setInt(1,reservation_id);
			ps.setInt(2,r.getCinema().getCinemaId());
			ps.setInt(3,r.getTimeslot().getTimeSlotID());

			for (int i = 0; i < numberOfSeats.size(); i++) {
				ps.setInt(4,numberOfSeats.get(i));
				ps.executeUpdate();
				//System.out.println("Seats Updated Successfully");
				res = 1;
			}	    	
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return res;
	}
	
	public static int selectReservationId(){
		int id = -1;
		try {
			PreparedStatement ps = connect.prepareStatement(SELECT_RESERVATION_ID);
			result = ps.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();

			if(result.next()) {
				id = result.getInt("reservation_id");
				//System.out.println("Successfully retrieved id " + id);
			}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return id;
	}
	
	public void confirmReservation(Reservation r, Customer c){
		try{
	//		System.out.println("Connecting to database. . .");
			PreparedStatement ps = connect.prepareStatement(ADDTO_DB);
	//		System.out.println("Inserting data. . .");
			ps.setInt(1, r.getCinema().getCinemaId());
			ps.setString(2, r.getTimeslot().getTimeStart());
			ps.setString(3, c.getCustomerName());
			ps.setInt(4, r.getTotalAmount());
			ps.setInt(5, r.getNoOfChildrens());
			ps.setInt(6, r.getNoOfAdults());
			ps.setInt(7, r.getNoOfSeniors());
			ps.setInt(8, r.getTimeslot().getTimeSlotID());

			ps.executeUpdate();

	//		System.out.println("Added successfully!");	//for testing
			
			ps.close();
			connect.close();

		}catch(SQLException e){
			e.printStackTrace();
		}

	}
	
 	
	public void viewSeats(int cinema_id, int timeslot_id){
		int columns = 7;
		int rows = 4;
		try {
			Statement stmt = connect.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT * FROM seats WHERE cinema_id ="+cinema_id+" && timeslot_id ="+timeslot_id+";");   
		    System.out.println("Seat Selection Info");
		    System.out.println("\n\nPlease choose your seats from the available seats below: ");
		    int i = 0;
		    while(rs.next()) {
		    	if(i < columns){
		    		if(rs.getInt(rows) == 0)
			    	{
			    		System.out.print(rs.getInt(2)+"\t");
			    	}
			    	else{
			    		System.out.print("-\t");
			    	}
		    		i++;
		    	} else {
		    		if(rs.getInt(rows) == 0)
			    	{
			    		System.out.println(rs.getInt(2));
			    	}
			    	else{
			    		System.out.println("-");
			    	}
		    		i = 0;
		    	}
		    }
		    	    
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			closeConnection();
		}
	}
	
	

	
	
	//for testing
	public static void main(String[]args){
//		DatabaseConnect db = new DatabaseConnect();
		
//	//	Timeslot: int timeSlotID, String timeStart	
//		Timeslot ts = new Timeslot(1011,"3:30PM");
//		
//	//	Movie: int movieID, String movieName, String movieDirector, String movieGenre, String movieRating
//		Movie movie1 = new Movie(3011, "How I Met Her", "Andrew E.", "Comedy", "PG");	
//		
//	//	Cinema: int cinemaId, Movie movie, int noOfSeats, String status	
//		Cinema cine = new Cinema(2011, movie1, 40, "VIEWING");							
//		
//	//	Timeslot timeslot, Cinema cinema, int seatId, int noOfChildrens, int noOfAdults, int noOfSeniors, int totalAmount	
//		Reservation res = new Reservation(ts, cine, 1, 0, 3, 0, 459);					
//		Customer cust = new Customer(4011, "Shaiapouf");

		
//		Customer cust = new Customer(2, "Belle Test");
//		Movie movie1 = new Movie(1,"Harry Potter", "You", "Adventure", "R");
//		Cinema cine = new Cinema(1, movie1, 40, "SHOWING");
//		ArrayList<Cinema> cines = new ArrayList<Cinema>();
//		cines.add(cine);
//		Timeslot ts = new Timeslot(2,"3:30PM", 1, 1);
//		
//		Reservation res = new Reservation(ts, cine, 14, 0, 2, 0, 300);
//		
//		db.confirmReservation(res, cust);
//		
//		db.confirmReservation(res, cust);
	
		//db.selectMovies();

		//int id = db.selectReservationId();
		
		//System.out.print(id);
		


//		ArrayList<Integer> rs = new ArrayList<Integer>();
//		rs.add(4);
//		rs.add(5);
//		rs.add(6);

		// ArrayList<Integer> rs = new ArrayList<Integer>();
		// rs.add(4);
		// rs.add(5);
		// rs.add(6);

		//db.updateSeats(id,1,1,rs);
		
		
		
	}

}
