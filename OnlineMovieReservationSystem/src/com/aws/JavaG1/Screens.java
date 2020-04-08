package com.aws.JavaG1;
import com.aws.JavaG1.utilities.Utility;
import com.aws.JavaG1.utilities.gui;
import java.text.SimpleDateFormat;
import java.util.*;

public class Screens {
    private static Scanner scanner = new Scanner(System.in);
    public static List<Integer> numberOfSeatsDisplay = new ArrayList<Integer>();
    public static byte choice = -1;
    public static Boolean register = false;
    private static byte WELCOME_CODE = -127;
    public static Reservation pendingReservation = null;

    public static String Screen1A() {
        String name = "";
        do {
            System.out.println("=============================================================");
            System.out.print("| * * *O N L I N E   M O V I E   R E S E R V A T I O N* * * |\n");
            System.out.println("=============================================================");
            System.out.print("\nPlease enter your name: ");
            name = scanner.nextLine();
            
            if (!Utility.isValidName(name)) {
            	System.out.println("Invalid name!");
            }
 
        } while (!Utility.isValidName(name));

        return name;
    }

    public static byte Screen1B(String name) {
        System.out.println("\n=============================================================");
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

    public static void Screen3(Customer customer, ArrayList<Cinema> cinemas) {
        choice = -1;

        while (choice != 3 || choice != 0 && choice != WELCOME_CODE) {
            choice = Screen3Menu(customer.getCustomerName());
            switch (choice) {
                case 1:
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
        if(pendingReservation == null)
           pendingReservation = new Reservation();

            do {
            	System.out.println("\n=============================================================");
                System.out.println("\nMovie Info");
                int cinemaID = 0;
                int timeslotID = 0;
                Cinema cinema = null;
                Timeslot timeslot = null;
                
                System.out.print("\nEnter Cinema ID (1 - " + cinemas.size() +"): "); 
                do {
                    	try {
                        	cinemaID = scanner.nextInt();
                        	cinema = Utility.getCinemaByID(cinemas, cinemaID);   
                            if (cinema == null) {
                            	System.out.println("Invalid cinema id!\nPlease enter a number between (1 - " + cinemas.size() +"): ");
                            }
 
                        	}catch (InputMismatchException e) {
                        		scanner.next();
                        		System.out.println("Please enter a whole number.");
                        	}	
             
                } while (cinema == null);
                
                pendingReservation.setCinema(cinema);
                
                System.out.print("\nEnter Timeslot entry (1 - " + cinema.getTimeslots().size() + "): ");
                do {
                    	try {
                    		timeslotID = scanner.nextInt();
                    		
                    		if (timeslotID != 0 && --timeslotID < cinema.getTimeslots().size()){
                    			timeslot = Utility.getTimeSlotById(pendingReservation.getCinema().getTimeslots(),
                                    cinema.getTimeslots().get(timeslotID).getTimeSlotID());
                    		}
                    		if (timeslot == null) {
                           	 System.out.println("Invalid timeslot id!\nPlease enter a number between (1 - " + cinema.getTimeslots().size() + "): ");
                           }
                    	} catch (InputMismatchException e) {
                    		scanner.next();
                    		System.out.println("Please enter a whole number.");
                    	}
                  
                } while (timeslot == null);
                
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
                        pendingReservation = null;
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            }	 
    }

    private static byte Screen3AMenu() {
    	System.out.println("\n=============================================================");
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
        	System.out.println("\n=============================================================");
            System.out.println("\nCustomer Info: ");
            
            int noChild = 0;
            System.out.print("\nEnter # of Kids: ");
            while(noChild == 0) {
            	try {
            		noChild = scanner.nextInt();
            		while(noChild < 0) {
            			System.out.print("\nPlease input a positive number: ");
            			noChild = scanner.nextInt();
            		}
            		reservation.setNoOfChildrens(noChild);
            	}catch(InputMismatchException e) {
            		scanner.next();
            		System.out.print("\nPlease input a number: ");
            	}
            }
            
            int noAdult = 0;
            System.out.print("Enter # of Adults: ");
            while(noAdult == 0) {
            	try {
                	noAdult = scanner.nextInt();
                	while(noAdult < 0) {
            			System.out.print("\nPlease input a positive number: ");
            			noAdult = scanner.nextInt();
            		}
                	reservation.setNoOfAdults(noAdult);
                }catch(InputMismatchException f) {
                	scanner.next();
            		System.out.print("\nPlease input a number: ");
                }
            }
            
            int noSenior = 0;
            System.out.print("Enter # of Senior: ");
            while(noSenior == 0) {
            	try {
            		noSenior = scanner.nextInt();
            		while(noSenior < 0) {
            			System.out.print("\nPlease input a positive number: ");
            			noSenior = scanner.nextInt();
            		}
            		reservation.setNoOfSeniors(noSenior);  
            	}catch(InputMismatchException g) {
            		scanner.next();
            		System.out.print("\nPlease input a number: ");
            	}
            }
    
           if (reservation.isCinemaFull()) {
        	   System.out.println("Cinema is Full, please lessen the no of attendees.");
           }
        } while (reservation.isCinemaFull());

        while (choice != 0 && choice != WELCOME_CODE) {
            choice = Screen3BMenu();
            switch (choice) {
                case 1:
                    Screen3C(customer, reservation, seats, cinemas);
                    break;
                case 2:
                    Screen3B(customer, cinemas, reservation, seats);
                    break;
                case 0:
                    pendingReservation = null;
                    choice = WELCOME_CODE;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;

            }
        }
    }

    private static byte Screen3BMenu() {
    	System.out.println("\n=============================================================");
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
        int addedCount = 0;
        int totalNumberOfSeats = 40;
        int noOfPeopleRes = reservations.getTotalPeople();
     
        if(!numberOfSeatsDisplay.isEmpty()){
        	numberOfSeatsDisplay.clear();
        }
        DatabaseConnect dbconn = new DatabaseConnect();
        dbconn.viewSeats(reservations.getCinema().getCinemaId(), reservations.getTimeslot().getTimeSlotID());
     
        while(addedCount != noOfPeopleRes){
        	Scanner input = new Scanner(System.in);
            System.out.println("\nYour choice: ");
            seat = input.nextInt();
            
            if (numberOfSeatsDisplay.contains(seat)) {
                System.out.println("\nOops! Seat Taken! Try again.");
            } else if(seat > totalNumberOfSeats) {
            	System.out.println("Oops! Seat does not exist! Try again.");
            } else if (seat < 0) {
            	System.out.println("Oops! Negative number! Try again.");
        	}else if(seat == 0) {
        		System.out.println("Oops! Try again.");
        	}else{
            	numberOfSeatsDisplay.add(seat);
            	addedCount++;
            }
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
                    pendingReservation = null;
                    choice = WELCOME_CODE;
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;

            }
        }
    }

    private static byte Screen3CMenu() {
    	System.out.println("\n=============================================================");
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
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        
        System.out.println("\n=============================================================");
        System.out.println("\nTicket info\n");
        System.out.println("Date: " + date);
        System.out.println("Movie: " + reservation.getCinema().getMovie().getMovieName()
                + "\nCinema #" + reservation.getCinema().getCinemaId()
                + " at " + reservation.getTimeslot().getTimeStart());
        System.out.println("No. of people: " + reservation.getTotalPeople());
        System.out.println("Seat(s): " + numberOfSeatsDisplay); 
        System.out.println("Total amount: P" + reservation.getTotalAmount());
     
        
        while (choice != 0 && choice != WELCOME_CODE) {

            choice = Screen4Menu();
            switch (choice) {

                case 1:
                    DatabaseConnect db4 = new DatabaseConnect();
                    db4.confirmReservation(reservation, customer);
                    customer.addReservation(reservation);
                    DatabaseConnect db5 = new DatabaseConnect();
                    int reservation_id = db5.selectReservationId();
                    db5.updateSeats(reservation_id, reservation, numberOfSeatsDisplay);
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
    	System.out.println("\n=============================================================");
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
        System.out.println("\n=============================================================");
        System.out.println("\n\nSeats reserved!");
        System.out.println("Thank you and have a great day! <3");

        System.out.println("Press Enter to make a new transaction");
        scanner.nextLine();


    }

    public static byte ScreenCMenu() {
    	System.out.println("\n=============================================================");
        System.out.println("\nPress 1 to go back to menu");
        System.out.println("Press 0 to Cancel");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

}