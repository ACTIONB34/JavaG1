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

    public static void Screen2(ArrayList<Cinema> cinemas){
        for(Cinema cinema: cinemas){
            System.out.println(cinema.toString());
        }
        System.out.println("\nPress Anykey to Continue!");
        String sy = scanner.nextLine();
    }

    public static void Screen3(String name) {
        byte choice = -1;

        while (choice != 3) {
            choice = Screen3Menu(name);
        switch(choice){
            case 1:
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


}