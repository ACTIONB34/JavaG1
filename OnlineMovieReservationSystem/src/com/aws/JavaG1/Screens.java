package com.aws.JavaG1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Screens {
    private static Scanner scanner = new Scanner(System.in);

    public static String Screen1A() {

        System.out.print("ONLINE MOVIE RESERVATION\n\n"
                + "Please enter your name: ");
        return scanner.nextLine();
    }

    public static byte Screen1B(String name) {
        System.out.print("Welcome, hello " + name + "!\n\n");
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to View Now Showing! Screen");
        System.out.println("Press 2 to View Reservation");
        System.out.println("Press 3 to Checkout Reservation");
        System.out.println("Press 0 to Exit\n");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    public static void Screen2(ArrayList<Cinema> cinemas) {
        for (Cinema cinema : cinemas) {
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
                    break;
                case 2:
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

    private static Cinema getCinemaById(ArrayList<Cinema> cinemas, int cinemaID) {
        for (Cinema cinema : cinemas) {
            if (cinema.getCinemaId() == cinemaID)
                return cinema;
        }

        return null;
    }

    private static Timeslot getTimeslotById(Cinema cinema, int timeslotID) {
        for (Timeslot timeslot : cinema.getTimeslots()) {
            if (timeslot.getTimeSlotID() == timeslotID)
                return timeslot;
        }
        return null;
    }

    public static void Screen3A(Customer customer, ArrayList<Cinema> cinemas) {
        byte choice = -1;
        Reservation reservation = new Reservation();

        do {
            System.out.print("\nEnter Cinema ID:");
            reservation.setCinema(getCinemaById(cinemas, scanner.nextInt()));
            System.out.print("\nEnter Timeslot ID:");
            reservation.setTimeslot(getTimeslotById(reservation.getCinema(), scanner.nextInt()));
            if (reservation.getCinema() == null)
                System.out.println("Invalid cinema Id");
            if (reservation.getTimeslot() == null)
                System.out.println("Invalid timeslot Id");
        } while (!reservation.isValidReservation());


        while (choice != 0) {
            choice = Screen3AMenu();
            switch (choice) {
                case 1:
                    Screen3B(customer, cinemas, reservation);
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

        System.out.println("Press 1 to Continue");
        System.out.println("Press 2 to Edit Movie Selection");
        System.out.println("Press 0 to Cancel\n");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }

    }

    private static void Screen3B(Customer customer, ArrayList<Cinema> cinemas, Reservation reservation) {
        byte choice = -1;
        do {
            System.out.print("\nEnter # of Childrens:");
            reservation.setNoOfChildrens(scanner.nextInt());
            System.out.print("\nEnter # of Adults:");
            reservation.setNoOfAdults(scanner.nextInt());
            System.out.print("\nEnter # of Senior:");
            reservation.setNoOfSeniors(scanner.nextInt());

            if (reservation.isCinemaFull())
                System.out.println("Cinema is Full, please lessen the no of attendees");
        } while (reservation.isCinemaFull());

        while (choice != 0 && choice != 2) {
            choice = Screen3BMenu();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    Screen2(cinemas);
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;

            }
        }

    }

    private static byte Screen3BMenu() {

        System.out.println("Press 1 to Continue to seat selection");
        System.out.println("Press 2 to go back to reservation screen");
        System.out.println("Press 0 to Viewing Movie Screen\n");
        System.out.print("Choice: ");
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }

    }

    public static void Screen3D(Customer customer, ArrayList<Cinema> cinemas) {
        byte choice = -1;
        System.out.println("No existing reservation");

        while (choice != 0) {
            choice = Screen3DMenu();
            switch (choice) {
                case 1:
                    Screen3A(customer, cinemas);
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

    public static void Screen4(Customer customer, ArrayList<Cinema> cinemas){

    }


}