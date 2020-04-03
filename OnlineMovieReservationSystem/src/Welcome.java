import com.aws.JavaG1.*;
import com.aws.JavaG1.utilities.Utility;

import java.util.ArrayList;

public class Welcome {
	
    public static void main(String[] args) {
        String Name = "";
        int choice = -1;
        Name = Screens.Screen1A();
        Customer customer = new Customer(123, Name);

        System.out.println("Loading Resources...\n");
        ArrayList<Movie> movies = DatabaseConnect.getAllMovies();
        ArrayList<Timeslot> timeslots = DatabaseConnect.getAllTimeSlots();
        ArrayList<Cinema> cinemas = DatabaseConnect.getAllCinemas();
        Utility.populateCinema(cinemas,timeslots,movies);


        while ( choice != 0) {
            choice = Screens.Screen1B(customer.getCustomerName());
            switch (choice) {
                case 1:
                    //Screen 2 - View Showing Movies
                    Screens.Screen2(cinemas);
                    break;
                case 2:
                    if(customer.getReservation() == null)
                       Screens.Screen3D(customer, cinemas);
                    else
                      Screens.Screen4(customer, cinemas, null);
                    break;
                case 3:
                    if (customer.getReservation() != null)
                    	Screens.Screen4(customer, cinemas, null);
                    else
                    	Screens.Screen3D(customer, cinemas);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("INVALID CHOICE!\n");
                    break;
            }
        }




    }

}