package com.aws.JavaG1;

import java.sql.*;



public class DatabaseConnect {
	
	private static Connection connect;
	private Statement statement;
	private static ResultSet result;

	private static String SELECT_MOVIES = "SELECT " + 
			"cinema_id, movie_name, movie_director, movie_rating,movie_genre " +
			"FROM movies, cinemas WHERE movies.movie_id = cinemas.movie_id " + 
			"AND movies.status = 1;";

	
	
	public DatabaseConnect() {
		this.connect = null;
		this.statement = null;
		this.result = null;
		
		try {
		    connect = DriverManager.getConnection("jdbc:mysql://localhost/moviereservation?useUnicode=true&"
		    		+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");
		    //System.out.println("CONN SUCCESS");
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
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
	    		System.out.print(result.getString("cinema_id") + "\t"
				    +    result.getString("movie_name") + "\t"
				    +    result.getString("movie_director") + "\t"
				    +    result.getString("movie_rating") + "\t"
				    +    result.getString("movie_genre"));
		    	System.out.println();
		    }
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
		
	}
	
	
	//for testing
	public static void main(String[]args){
		DatabaseConnect db = new DatabaseConnect();
		
		db.selectMovies();
		
	}

}
