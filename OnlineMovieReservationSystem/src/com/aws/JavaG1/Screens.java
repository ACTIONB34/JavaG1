package com.aws.JavaG1;

import com.aws.JavaG1.utilities.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Screens {
    private static Scanner scanner = new Scanner(System.in);
    public static List<Integer> numOfSeats = new ArrayList<Integer>();
    
    public static String Screen1A() {

        System.out.print("ONLINE MOVIE RESERVATION\n\n"
                + "Please enter your name: ");
        return scanner.nextLine();
    }

    public static byte Screen1B(String name) {
    	System.out.println("\n\n------------------------------------------");
        System.out.print("\nWelcome, hello " + name + "!\n\n");
        System.out.println("What would you like to do?\n");
        System.out.println("Press 1 to View available movies");
        System.out.println("Press 2 to View reservation");
        System.out.println("Press 3 to Checkout");
        System.out.println("Press 0 to Exit\n");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    public static void Screen2(ArrayList<Cinema> cinemas) {





        for(Cinema cinema: cinemas){
            System.out.println(cinema.toString());
        }

        System.out.print("\nPress Enter to Continue!");
        scanner.nextLine();
        scanner.nextLine(); // Double nextLine since previous read was a byte, doesn't read newline
    }

    public static void Screen3(Customer customer, ArrayList<Cinema> cinemas) {
        byte choice = -1;

        while (choice != 3) {
            choice = Screen3Menu(customer.getCustomerName());
            switch (choice) {
                case 1:
                    // Screen 3A - Make reservation
                    Screen3A(customer, cinemas);
                    choice = 0;
                    break;
                case 2:
                    if (customer.getReservation() == null)
                        Screen3D(customer, cinemas);
                    else
                       Screen3B(customer, cinemas, customer.getReservation(), null);

                    choice = 0;
                    break;
                case 3:
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        }
    }

    private static byte Screen3Menu(String name) {

        System.out.println("Press 1 to Reserve a Movie");
        System.out.println("Press 2 to Edit Existing Reservation");
        System.out.println("Press 3 to Back to Menu");
        System.out.println("Press 0 to Exit\n");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }

    }



    public static void Screen3A(Customer customer, ArrayList<Cinema> cinemas) {
        byte choice = -1;
        Reservation reservation;
        if(customer.getReservation() == null)
              reservation = new Reservation();
        else
              reservation = customer.getReservation();

        do {
        	System.out.println("\nMovie Info");
            System.out.print("\nEnter Cinema ID:");
            reservation.setCinema(Utility.getCinemaByID(cinemas, scanner.nextInt()));
            System.out.print("Enter Timeslot ID:");
            reservation.setTimeslot(Utility.getTimeSlotById(reservation.getCinema().getTimeslots(), scanner.nextInt()));
            
            if (reservation.getCinema() == null)
                System.out.println("Invalid cinema Id");
            
            if (reservation.getTimeslot() == null)
                System.out.println("Invalid timeslot Id");
        } while (!reservation.isValidReservation());


        while (choice != 0) {
            choice = Screen3AMenu();
            switch (choice) {
                case 1:
                    Screen3B(customer, cinemas, reservation, null);
                    choice = 0;
                    break;
                case 2:
                    Screen3A(customer, cinemas);
                    choice = 0;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private static byte Screen3AMenu() {
        System.out.println("\nPress 1 to Proceed to Customer Info");
        System.out.println("Press 2 to Change Movie Selection");
        System.out.println("Press 0 to Cancel\n");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }

    }

    private static void Screen3B(Customer customer, ArrayList<Cinema> cinemas, Reservation reservation, Seat seats) {
        byte choice = -1;
        do {
        	System.out.println("\nCustomer Info:");
            System.out.print("\nEnter # of Kids:");
            reservation.setNoOfChildrens(scanner.nextInt());
            System.out.print("Enter # of Adults:");
            reservation.setNoOfAdults(scanner.nextInt());
            System.out.print("Enter # of Senior:");
            reservation.setNoOfSeniors(scanner.nextInt());

            if (reservation.isCinemaFull())
                System.out.println("Cinema is Full, please lessen the no of attendees");
        } while (reservation.isCinemaFull());

        while (choice != 0) {
            choice = Screen3BMenu();
            switch (choice) {
                case 1:
                	//to do
                    Screen3C(customer, reservation, seats, cinemas);
                    choice = 0;
                    break;
                case 2:
                	//to do
                    Screen3B(customer, cinemas, reservation, seats);
                    choice = 0;
                    break;
                case 0:
                	//to do
                    Screen2(cinemas);
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;

            }
        }
    }

    private static byte Screen3BMenu() {

        System.out.println("\nPress 1 to Proceed to Seat Selection");
        System.out.println("Press 2 to Edit Number of People");
        System.out.println("Press 0 to Cancel\n");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }

    }
    
    public static void Screen3C(Customer customer, Reservation reservations, Seat seats, ArrayList<Cinema> cinemas) {
    	byte choice = -1;
    	int noOfPeopleRes = reservations.getTotalPeople();
    	int seat;
	    
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/moviereservation?"
		       		+ "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		    		   "root", "awsys+123");
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM seats WHERE cinema_id ="+reservations.getCinema().getCinemaId()+" && timeslot_id ="+reservations.getTimeslot().getTimeSlotID()+";");   
		    System.out.println("Seat Selection Info");
		    System.out.println("\n\nPlease choose your seats from the available seats below: ");
		    int i = 0;
		    while(rs.next()) {
		    	if(i < 7){
		    		if(rs.getInt(4) == 0)
			    	{
			    		System.out.print(rs.getInt(1)+"\t");
			    	}
			    	else{
			    		System.out.print("-\t");
			    	}
		    		i++;
		    	}
		    	else{
		    		if(rs.getInt(4) == 0)
			    	{
			    		System.out.println(rs.getInt(1));
			    	}
			    	else{
			    		System.out.println("-");
			    	}
		    		
		    		i = 0;
		    	}
		    }
		    
		    for(int i1 = 0; i1 < noOfPeopleRes; i1++) {
		    	Scanner input = new Scanner(System.in);
		    	System.out.println();
		    	System.out.println("Your Choice: ");
		    	seat = input.nextInt();
		    	numOfSeats.add(seat);
		    }
		    
		    

			
		
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
    
    	
    	
    	while (choice != 0) {
            choice = Screen3CMenu();
            switch (choice) {
                case 1:
                	Screen4(customer, cinemas, reservations);
                    choice = 0;
                    break;
                case 2:
                	Screen3C(customer, reservations, seats, cinemas);
                    choice = 0;
                    break;
                case 0:
                	Screen2(cinemas);
                	choice = 0;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;

            }
        } 	
    }
    
    private static byte Screen3CMenu() {

        System.out.println("\nPress 1 to Proceed to Checkout");
        System.out.println("Press 2 to Change Seat Selection");
        System.out.println("Press 0 to Cancel\n");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }

    }

    public static void Screen3D(Customer customer, ArrayList<Cinema> cinemas) {
        byte choice = -1;
        System.out.println("No existing reservation.\n");

        while (choice != 0) {
            choice = Screen3DMenu();
            switch (choice) {
                case 1:
                    Screen3A(customer, cinemas);
                    choice = 0;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;

            }
        }

    }

    private static byte Screen3DMenu() {

        System.out.println("Press 1 to Create Reservation");
        System.out.println("Press 0 to Cancel");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }

    }

    public static void Screen4(Customer customer, ArrayList<Cinema> cinemas, Reservation reservation) {
        byte choice = -1;
        System.out.println("Ticket info\n");
        System.out.println("Date: " + new Date());
        System.out.println("Movie: " + reservation.getCinema().getMovie().getMovieName()
        				 + " @" + "Cinema " + reservation.getCinema().getCinemaId()
        				 + " " + reservation.getTimeslot().getTimeStart());
        System.out.println("Total no. of people: " + reservation.getTotalPeople());
        System.out.println("Total amount: P" + reservation.getTotalAmount());
        System.out.println("Seat: " + numOfSeats);  // to add

        while (choice != 0) {
            choice = Screen4Menu();
            switch (choice) {

                case 1:
                	//call confirm function here
                	DatabaseConnect db2 = new DatabaseConnect();
                	db2.confirmReservation(reservation,customer);
                    customer.setReservation(reservation);
                    ScreenC();
                    choice = 0;
                    break;
                case 2:
                    Screen3A(customer, cinemas);
                    choice = 0;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }


    public static byte Screen4Menu() {

        System.out.println("\nPress 1 to Confirm");
        System.out.println("Press 2 to Edit Reservation");
        System.out.println("Press 0 to cancel");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    public static void ScreenC() {
        byte choice = -1;
        System.out.println("Seats reserved!");
        System.out.println("Thank you and have a great day!");
        
        System.out.println("Press Enter to make a new transaction");
        scanner.nextLine();
        

    }



	public static byte ScreenCMenu() {

        System.out.println("Press 1 to go back to menu");
        System.out.println("Press 0 to Cancel");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }
    }


}