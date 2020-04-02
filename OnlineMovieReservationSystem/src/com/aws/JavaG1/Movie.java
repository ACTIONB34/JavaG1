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
	private String movieDirector;
	private String movieGenre;
	private String movieRating;
	public char[] movieId;

	public Movie() {
	}

	public Movie(int movieID, String movieName, String movieDirector, String movieGenre, String movieRating) {
		this.movieID = movieID;
		this.movieName = movieName;
		this.movieDirector = movieDirector;
		this.movieGenre = movieGenre;
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

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}


}
