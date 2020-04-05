package com.aws.JavaG1;

import com.aws.JavaG1.utilities.Utility;
import com.aws.JavaG1.utilities.gui;

import java.util.*;

public class Screens {
    private static Scanner scanner = new Scanner(System.in);
    public static List<Integer> numberOfSeats = new ArrayList<Integer>();
    public static byte choice = -1;
    public static Boolean register = false;
    private static byte WELCOME_CODE = -127;
    public static Reservation pendingReservation = null;
    
    public static String Screen1A() {

        System.out.print("ONLINE MOVIE RESERVATION\n\n"
                + "Please enter your name: ");
        return scanner.nextLine();
    }

    public static byte Screen1B(String name) {
    	System.out.println("\n------------------------------------------");
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
            scanner.nextLine();
            return -1;
        }
    }

//    public static void Screen2(ArrayList<Cinema> cinemas) {
//
//        for(Cinema cinema: cinemas){
//            System.out.println(cinema.toString());
//        }
//
//        System.out.print("\nPress Enter to Continue!");
//        scanner.nextLine();
//        scanner.nextLine(); // Double nextLine since previous read was a byte, doesn't read newline
//
//    }

    public static void Screen3(Customer customer, ArrayList<Cinema> cinemas) {
        choice = -1;

        while (choice != 3 || choice != 0 && choice != WELCOME_CODE) {
            choice = Screen3Menu(customer.getCustomerName());
            switch (choice) {
                case 1:
                    // Screen 3A - Make reservation
                    Screen3A(customer, cinemas);
                    break;
                case 2:
                    if (pendingReservation == null)
                        Screen3D(customer, cinemas);
                    else
                       Screen3B(customer, cinemas, pendingReservation, null);

                    break;
                case 3:
                    choice = WELCOME_CODE;
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
            scanner.nextLine();
            return -1;
        }

    }



    public static void Screen3A(Customer customer, ArrayList<Cinema> cinemas) {
        choice = -1;

        pendingReservation = new Reservation();


        do {
        	System.out.println("\nMovie Info");
            int cinemaID = 0;
            int timeslotID = 0;
            Cinema cinema = null;
            Timeslot timeslot = null;

            do {
                System.out.print("\nEnter Cinema ID: ");
                cinemaID = scanner.nextInt();
                cinema = Utility.getCinemaByID(cinemas, cinemaID);
                if(cinema == null)
                    System.out.println("Invalid cinema id!");
            } while( cinema == null);
            pendingReservation.setCinema(cinema);
            do {
                System.out.print("\nEnter Timeslot ID: ");
                timeslotID = scanner.nextInt();
                timeslot = Utility.getTimeSlotById(pendingReservation.getCinema().getTimeslots(), timeslotID);
                if(timeslot == null)
                    System.out.println("Invalid timeslot id!");
            } while( timeslot == null);
            pendingReservation.setTimeslot(timeslot);

        } while (!pendingReservation.isValidReservation());


        while (choice != 0 && choice != WELCOME_CODE) {
            choice = Screen3AMenu();
            switch (choice) {
                case 1:
                    Screen3B(customer, cinemas, pendingReservation, null);
                    break;
                case 2:
                    Screen3A(customer, cinemas);
                    break;
                case 0:
                    choice = WELCOME_CODE;
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
            scanner.nextLine();
            return -1;
        }

    }

    private static void Screen3B(Customer customer, ArrayList<Cinema> cinemas, Reservation reservation, ArrayList<Seat> seats) {
        choice = -1;
        do {
        	System.out.println("\nCustomer Info: ");
            System.out.print("\nEnter # of Kids: ");
            reservation.setNoOfChildrens(scanner.nextInt());
            System.out.print("Enter # of Adults: ");
            reservation.setNoOfAdults(scanner.nextInt());
            System.out.print("Enter # of Senior: ");
            reservation.setNoOfSeniors(scanner.nextInt());

            if (reservation.isCinemaFull())
                System.out.println("Cinema is Full, please lessen the no of attendees.");
        } while (reservation.isCinemaFull());

        while (choice != 0 && choice != WELCOME_CODE) {
            choice = Screen3BMenu();
            switch (choice) {
                case 1:
                	//to do
                    Screen3C(customer, reservation, seats, cinemas);
                    break;
                case 2:
                	//to do
                    Screen3B(customer, cinemas, reservation, seats);
                    break;
                case 0:
                    //to do
                    choice = WELCOME_CODE;
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
            scanner.nextLine();
            return -1;
        }

    }
    
    public static void Screen3C(Customer customer, Reservation reservations, ArrayList<Seat> seats, ArrayList<Cinema> cinemas) {
        choice = -1;
    	int seat;
    	int totalNumberOfSeats = 40;
    	int noOfPeopleRes = reservations.getTotalPeople();
    	
    	DatabaseConnect dbconn = new DatabaseConnect();
    	dbconn.viewSeats(reservations.getCinema().getCinemaId(), reservations.getTimeslot().getTimeSlotID());

    	for(int i = 0; i < noOfPeopleRes;i++){
    		
    		Scanner input = new Scanner(System.in);
    		System.out.println("\nYour choice: ");
    		seat = input.nextInt();
    		
    		if(numberOfSeats.contains(seat)) {
    			System.out.println("\nOops! Seat Taken! Try again.");
    			Scanner input1 = new Scanner(System.in);
        		System.out.println("\nYour choice: ");
        		seat = input.nextInt();
    		} 
    		
    		if(seat > totalNumberOfSeats){
    			System.out.println("\nSeat number does not exist. Try again.");
    			Scanner input2 = new Scanner(System.in);
        		System.out.println("\nYour choice: ");
        		seat = input.nextInt();
    		}
    		numberOfSeats.add(seat);
    	}

    	while (choice != 0 && choice != WELCOME_CODE) {
            choice = Screen3CMenu();
            switch (choice) {
                case 1:
                	Screen4(customer, cinemas, reservations);
                    break;
                case 2:
                	Screen3C(customer, reservations, seats, cinemas);
                    break;
                case 0:
                	gui nowShowing = new gui(cinemas);
                	choice = WELCOME_CODE;
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
            scanner.nextLine();
            return -1;
        }

    }

    public static void Screen3D(Customer customer, ArrayList<Cinema> cinemas) {
        choice = -1;
        System.out.println("No existing reservation.\n");

        while (choice != 0 && choice != WELCOME_CODE) {
            choice = Screen3DMenu();
            switch (choice) {
                case 1:
                    Screen3A(customer, cinemas);
                    break;
                case 0:
                    choice = WELCOME_CODE;
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
            scanner.nextLine();
            return -1;
        }

    }

    public static void Screen4(Customer customer, ArrayList<Cinema> cinemas, Reservation reservation) {
        choice = -1;
        System.out.println("Ticket info\n");
        System.out.println("Date: " + new Date());
        System.out.println("Movie: " + reservation.getCinema().getMovie().getMovieName()
        				 + " @" + "Cinema " + reservation.getCinema().getCinemaId()
        				 + " " + reservation.getTimeslot().getTimeStart());
        System.out.println("Total no. of people: " + reservation.getTotalPeople());
        System.out.println("Total amount: P" + reservation.getTotalAmount());
        System.out.println("Seat: " + numberOfSeats);  // to add
    	
        while (choice != 0 && choice != WELCOME_CODE) {

            choice = Screen4Menu();
            switch (choice) {

                case 1:
                	DatabaseConnect db4 = new DatabaseConnect();
                	db4.confirmReservation(reservation, customer);
                    customer.addReservation(reservation);
                    DatabaseConnect db5 = new DatabaseConnect();
                    int reservation_id = db5.selectReservationId();
                    db5.updateSeats(reservation_id, reservation, numberOfSeats);
                    numberOfSeats.clear();
                    pendingReservation = null;
                    ScreenC();
                    register = true;
                    choice = WELCOME_CODE;
                    break;
                case 2:
                    Screen3A(customer, cinemas);
                    break;
                case 0:
                    choice = WELCOME_CODE;
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
            scanner.nextLine();
            return -1;
        }
    }

    public static void ScreenC() {
        choice = -1;
        System.out.println("\n------------------------------------------\n");
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
            scanner.nextLine();
            return -1;
        }
    }

//	public static void main(String[] args) {
//		//for testing only
//		Customer cust = new Customer(2, "Isa Test");
//		Movie movie1 = new Movie(1,"Harry Potter", "You", "Adventure", "R");
//		Cinema cine = new Cinema(1, movie1, 40, "SHOWING");
//		ArrayList<Cinema> cines = new ArrayList<Cinema>();
//		cines.add(cine);
//		Timeslot ts = new Timeslot(2,"3:30PM", 1, 1);
//		
//		Reservation res = new Reservation(ts, cine, 14, 0, 2, 0, 300);
//		
//		Screens.Screen4(cust, cines, res);
//	}	

}