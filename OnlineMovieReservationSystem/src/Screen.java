import com.aws.JavaG1.Customer;
import com.aws.JavaG1.Reservation;
import com.aws.JavaG1.Screens;

public class Screen {


    public static void main(String[] args) {
        String Name = "";
        int choice = -1;
        Name = Screens.Screen1A();
        Customer customer = new Customer(123, Name);
        Reservation reservation;

        while (choice != 0) {
            choice = Screens.Screen1B(customer.getCustomerName());
            switch (choice) {
                case 1:
                    //Screen 2
                    Screens.Screen2(Name);
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