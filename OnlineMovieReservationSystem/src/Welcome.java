import com.aws.JavaG1.*;
import com.aws.JavaG1.utilities.Utility;
import com.aws.JavaG1.utilities.gui;

import java.util.ArrayList;

public class Welcome {

    public static void displayReservations(ArrayList<Reservation> reservations){
        if(reservations.size() > 0){
            for(Reservation reservation: reservations){
                System.out.println("==============================");
                System.out.println(reservation.toString());
                System.out.println("==============================");

            }
        }
    }

    public static void main(String[] args) {
        String name = "";
        Customer customer = null;
        ArrayList<Movie> movies = null;
        ArrayList<Timeslot> timeslots = null;
        ArrayList<Cinema> cinemas = null;
        ArrayList<Reservation> reservations = null;
        Screens.register = true;

        while (Screens.choice != 0) {
            if (Screens.register) {
                name = Screens.Screen1A();
                customer = new Customer(123, name);
                System.out.println("Loading Resources...\n");
                movies = DatabaseConnect.getAllMovies();
                timeslots = DatabaseConnect.getAllTimeSlots();
                cinemas = DatabaseConnect.getAllCinemas();
                Utility.populateCinema(cinemas, timeslots, movies);
                reservations = DatabaseConnect.getAllReservationByName(name,timeslots, cinemas);

                displayReservations(reservations);

                customer.setReservations(reservations);

                Screens.register = false;
            }

            Screens.choice = Screens.Screen1B(customer.getCustomerName());
            switch (Screens.choice) {
                case 1:
                    //Screen 2 - View Showing Movies
                    //Screens.Screen2(cinemas);
                	gui nowShowing = new gui(cinemas);
                    break;
                case 2:
                    if (Screens.pendingReservation == null)
                        Screens.Screen3D(customer, cinemas);
                    else
                        Screens.Screen4(customer, cinemas, Screens.pendingReservation);
                    break;
                case 3:
                    if (Screens.pendingReservation == null)
                        Screens.Screen3D(customer, cinemas);
                    else
                    	Screens.Screen4(customer, cinemas, Screens.pendingReservation);
                   
                    break;
                case 0:
                    break;
                default:
                    System.out.println("INVALID CHOICE!\n");
                    Screens.choice = -127;
                    break;
            }
        }


    }

}