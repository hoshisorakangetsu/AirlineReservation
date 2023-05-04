package com.mycompany.airlinereservation.util;

import java.util.InputMismatchException;
import java.util.Scanner;

// utility class to help get input faster with code (write one line instead of 2)
// handles exception as well
public class ConsoleInput {
    private static Scanner scanner = new Scanner(System.in);

    // I know it will be needed ;)
    public static void clearBuffer() {
        scanner.next();
    }

    // won't appear InputMismatchException, other Exception is unexpected, so cannot
    // handle
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: Please enter a valid integer");
                scanner.nextLine(); // consume invalid token
            }
        }
    }

    public static double getDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: Please enter a valid decimal");
                scanner.nextLine(); // consume invalid token
            }
        }
    }

    public static char getChar(String prompt) {
        String res = scanner.nextLine();
        if (res.length() > 1) {
            System.out.println("Input has more than one character, ignoring the rest...");
        }
        return res.charAt(0);
    }

    // returns the number of that choice
    public static int getChoice(Choicer[] choices, String prompt) {
        PrettyPrint.printOptions(choices);
        return ConsoleInput.getInt(prompt);  // instead of using scanner since getInt alrd does validation
    }
}
