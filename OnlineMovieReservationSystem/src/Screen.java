import com.aws.JavaG1.*;

import java.util.ArrayList;

public class Screen {



    public static void main(String[] args) {
        String Name = "";
        int choice = -1;
        Name = Screens.Screen1A();
        Customer customer = new Customer(123, Name);
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
        ArrayList<Timeslot> timeslot = new ArrayList<Timeslot>();
        Movie IT = new Movie(1010, "IT", "horror", "1hr", "5 stars");
        Movie MonsterInc = new Movie(1011, "MonsterInc", "Action", "2hr", "5 stars");
        Movie PPG = new Movie(1012, "PPG", "Action", "3hr", "4 stars");
        timeslot.add(new Timeslot(10, "11AM", "2PM"));
        timeslot.add(new Timeslot(20, "3pM", "5PM"));
        timeslot.add(new Timeslot(30, "6PM", "11PM"));
        cinemas.add(new Cinema(1,IT, timeslot, 40, "OPEN"));
        cinemas.add(new Cinema(2,MonsterInc, timeslot, 40, "OPEN"));
        cinemas.add(new Cinema(3,PPG, timeslot, 40, "OPEN"));


        while (choice != 0) {
            choice = Screens.Screen1B(customer.getCustomerName());
            switch (choice) {
                case 1:
                    //Screen 2 - View Showing Movies
                    Screens.Screen2(cinemas);
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
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