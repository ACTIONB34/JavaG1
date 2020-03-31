/**
 * 
 */

/**
 * @author chanel.baluyos
 *
 */
public class Cinema {

	int cinemaID;
	int movieID;
	int timeslotID;
	int status;
	
	public Cinema(int cinemaID, int movieID, int timeslotID, int status) {
		this.cinemaID = cinemaID;
		this.movieID = movieID;
		this.timeslotID = timeslotID;
		this.status = status;
	}

	public int getCinemaID() {
		return cinemaID;
	}

	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public int getTimeslotID() {
		return timeslotID;
	}

	public void setTimeslotID(int timeslotID) {
		this.timeslotID = timeslotID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	

}
