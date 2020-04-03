import com.aws.JavaG1.*;

import java.util.ArrayList;

public class Welcome {
	
    public static void main(String[] args) {
        String Name = "";
        int choice = -1;
        Name = Screens.Screen1A();
        Customer customer = new Customer(123, Name);






        ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
        ArrayList<Timeslot> timeslot = new ArrayList<Timeslot>();

        Movie IT = new Movie(1010, "IT", "horror", "1hr", "SPG");
        Movie MonsterInc = new Movie(1011, "MonsterInc", "Action", "2hr", "G");
        Movie PPG = new Movie(1012, "PPG", "Action", "3hr", "PG");
//        timeslot.add(new Timeslot(1, "12:00 PM", "3:00 PM"));
//        timeslot.add(new Timeslot(2, "3:30 PM", "6:30 PM"));
//        timeslot.add(new Timeslot(3, "7:00 PM", "10:00 PM"));
        cinemas.add(new Cinema(1,IT, timeslot, 40, "OPEN"));
        cinemas.add(new Cinema(2,MonsterInc, timeslot, 40, "OPEN"));
        cinemas.add(new Cinema(3,PPG, timeslot, 40, "OPEN"));


        while ( choice != 0) {
            choice = Screens.Screen1B(customer.getCustomerName());
            switch (choice) {
                case 1:
                    //Screen 2 - View Showing Movies
                    Screens.Screen2();
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