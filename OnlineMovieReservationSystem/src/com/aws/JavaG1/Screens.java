package com.aws.JavaG1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Screens {
    private static Scanner obj = new Scanner(System.in);

    public static String Screen1A() {

        System.out.print("ONLINE MOVIE RESERVATION\n\n"
                + "Please enter your name: ");
        return obj.nextLine();
    }

    public static byte Screen1B(String name) {
        System.out.print("Welcome, hello " + name + "!\n\n\n");
        System.out.println("What would you like to do?\n\n");
        System.out.println("Press 1 to View Now Showing! Screen");
        System.out.println("Press 2 to View Reservation\n");
        System.out.println("Press 3 to Checkout Reservation\n\n");
        System.out.println("Press 0 to Exit\n\n");
        System.out.println("Choice: ");
        try {
            return obj.nextByte();
        } catch (InputMismatchException e) {
            return -1;
        }
    }


}
