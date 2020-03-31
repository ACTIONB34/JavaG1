import java.util.*;

public class Screen {

	public String Screen1A(){
		Scanner obj = new Scanner(System.in);
		System.out.print("ONLINE MOVIE RESERVATION\n\n"
		               + "Please enter your name: ");
		return obj.nextLine();
	}
	
	public int Screen1B(String name) {
		Scanner obj = new Scanner(System.in);
		System.out.print("Welcome, hello " + name + "!\n\n\n");

		
		System.out.println("What would you like to do?\n\n"
						+  "Press 1 to View Now Showing! Screen\n"
						+  "Press 2 to View Reservation\n"
						+  "Press 3 to Checkout Reservation\n\n");
		int choice = obj.nextInt();
		
		return choice;
	
	}
	
	public static void main(String[] args){
		String username;
		int choice;
		
		Screen s = new Screen();
		
		username = s.Screen1A();
		choice = s.Screen1B(username);
		
		switch (choice){
		case 1:
			System.out.println("1");
			break;
		case 2:
			System.out.println("2");
			break;
		case 3:
			System.out.println("3");
			break;
		case 0:
			System.out.println("Bye!");
		}
		
		
		/*for checking*/
		System.out.println(username + " " + choice);
	}
	
}
