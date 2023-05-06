package com.mycompany.airlinereservation.util;

import java.util.InputMismatchException;
import java.util.Scanner;

// utility class to help get input faster with code (write one line instead of 2)
// handles exception as well
public class ConsoleInput {
    private static Scanner scanner = new Scanner(System.in);

    // I know it will be needed ;)
    public static void clearBuffer() {
        // be more explicit, reinit the scanner object
        scanner = new Scanner(System.in);
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
        System.out.print(prompt);
        String res = scanner.nextLine();
        if (res.length() > 1) {
            System.out.println("Input has more than one character, ignoring the rest...");
        }
        return res.charAt(0);
    }

    // returns the number of that choice
    public static int getChoice(Choicer[] choices, String prompt) {
        while (true) {
            try {
                PrettyPrint.printOptions(choices);
                int choice = ConsoleInput.getInt(prompt);  // instead of using scanner since getInt alrd does validation
                if (choice > choices.length || choice <= 0) {
                    throw new InputOutOfRangeException(1, choices.length);
                }
                return choice;
            } catch (InputOutOfRangeException ioore) {
                System.out.println(ioore.getMessage());
            }
        }
    }
}

// only used in this file, to be thrown when user enters a number outside of the range
class InputOutOfRangeException extends Exception {
    InputOutOfRangeException(int min, int max) {
        super("The given input is out of the range " + min + " " + max);
    }
}
