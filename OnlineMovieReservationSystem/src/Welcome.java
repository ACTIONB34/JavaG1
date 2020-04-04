import com.aws.JavaG1.*;
import com.aws.JavaG1.utilities.Utility;

import java.util.ArrayList;

public class Welcome {

    public static void main(String[] args) {

        int choice = -1;
        String name = "";
        Customer customer = null;
        ArrayList<Movie> movies = null;
        ArrayList<Timeslot> timeslots = null;
        ArrayList<Cinema> cinemas = null;

        while (choice != 0) {
            if (choice != 1) {
                name = Screens.Screen1A();
                customer = new Customer(123, name);
                System.out.println("Loading Resources...\n");
                movies = DatabaseConnect.getAllMovies();
                timeslots = DatabaseConnect.getAllTimeSlots();
                cinemas = DatabaseConnect.getAllCinemas();
                Utility.populateCinema(cinemas, timeslots, movies);
            }

            choice = Screens.Screen1B(customer.getCustomerName());
            switch (choice) {
                case 1:
                    //Screen 2 - View Showing Movies
                    Screens.Screen2(cinemas);
                    break;
                case 2:
                    if (customer.getReservation() == null)
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