import java.util.*;

public class Screen {

	public void Screen1(){
		Scanner getName = new Scanner(System.in);
		System.out.print("ONLINE MOVIE RESERVATION\n\n"
		               + "Please enter your name: ");
		String userName = getName.nextLine();
	
		System.out.print("Welcome, hello " + userName + "!\n\n\n");

		getName.close();
		System.out.println("What would you like to do?\n\n"
						+  "Press 1 to View Now Showing! Screen\n"
						+  "Press 2 to View Reservation\n"
						+  "Press 3 to Checkout Reservation\n\n");
		
		
	}
	
	public static void main(String[] args){
		Screen s = new Screen();
		s.Screen1();
	}
	
}
