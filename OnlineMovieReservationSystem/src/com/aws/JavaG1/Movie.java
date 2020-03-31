package com.aws.JavaG1;

/**
 * 
 */

/**
 * @author kristiane.paradela
 *
 */
public class Movie {


	private int movieID;
	private String movieName;
	private String movieGenre;
	private String movieLength;
	private String movieRating;

	public Movie(int movieID, String movieName, String movieGenre, String movieLength, String movieRating) {
		this.movieID = movieID;
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieLength = movieLength;
		this.movieRating = movieRating;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getMovieLength() {
		return movieLength;
	}

	public void setMovieLength(String movieLength) {
		this.movieLength = movieLength;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	//	public static void main(String[] args){
//		Connection conn = null;
//		Statement smt = null;
//		ResultSet rs = null;
//		try {
//		    conn =
//		       DriverManager.getConnection("jdbc:mysql://localhost/moviereservations?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
//		    		   "root", "awsys+123");
//
//		    smt = conn.createStatement();
//		    rs = smt.executeQuery("select * from movies;");
//
//		    while(rs.next()) {
//		    	System.out.println(rs.getString(2));
//		    }
//
//		} catch (SQLException ex) {
//		    // handle any errors
//		    System.out.println("SQLException: " + ex.getMessage());
//		    System.out.println("SQLState: " + ex.getSQLState());
//		    System.out.println("VendorError: " + ex.getErrorCode());
//		}
//	}

}
