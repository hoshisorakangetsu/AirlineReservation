package com.mycompany.airlinereservation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

// utility class to help get input faster with code (write one line instead of 2)
// handles exception as well
public class ConsoleInput {
    private static Scanner scanner = new Scanner(System.in);

    // I know it will be needed ;)
    public static void reInit() {
        // be more explicit, reinit the scanner object
        scanner = new Scanner(System.in);
    }

    // won't appear InputMismatchException, other Exception is unexpected, so cannot
    // handle
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String getString(String prompt, boolean allowEmpty) {
        String res;
        do {
            res = getString(prompt);
            if (res.isBlank())
                System.out.println("No empty inputs are allowed");
        } while(!allowEmpty && res.isBlank());
        return res;
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

    // asks for an int between min and max, inclusive
    public static int getInt(String prompt, int min, int max) {
        while (true) {
            int res = getInt(prompt);
            if (res >= min || res <= max) {
                return res;
            }
            System.out.printf("Invalid input: Please enter an integer in between (%d, %d) inclusive\n", min, max);
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

    public static Date getDate(String prompt) {
        while (true) {
            try {
                // if user didnt provide prompt use the default prompt
                System.out.print(prompt == null ? "Please enter a date (dd/mm/yyyy): " : prompt);
                String inDate = scanner.nextLine();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.parse(inDate);
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
                System.out.println("Please enter a valid date using the correct format");
            }
        }
    }

    public static Date getDateTime(String prompt) {
        while (true) {
            try {
                System.out.print(
                    prompt == null 
                        ? "Please enter a date time (dd/MM/yyyy HH:mm): " 
                        : prompt
                );
                String inDate = scanner.nextLine();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                return sdf.parse(inDate);
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
                System.out.println("Please enter a valid date using the correct format");
            }
        }
    }

    public static char getChar(String prompt) {
        while (true) {
            System.out.print(prompt);
            String res = scanner.nextLine();
            if (res.isBlank()) {
                System.out.println("Input is empty, please reenter");
                continue;
            }
            if (res.length() > 1) {
                System.out.println("Input has more than one character, ignoring the rest...");
            }
            return res.charAt(0);
        }
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
