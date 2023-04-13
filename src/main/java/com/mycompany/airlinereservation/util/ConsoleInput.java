package util;

import java.util.InputMismatchException;
import java.util.Scanner;

// utility class to help get input faster with code (write one line instead of 2)
// handles exception as well
public class ConsoleInput {
  private static Scanner scanner = new Scanner(System.in);

  // won't appear InputMismatchException, other Exception is unexpected, so cannot handle
  public static String getString(String prompt) {
    return scanner.nextLine();
  }

  public static int getInt(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        return scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Invalid input: Please enter a valid integer");
        scanner.nextLine();  // consume invalid token
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
        scanner.nextLine();  // consume invalid token
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

  public static <T> T getChoice(T[] choices, String prompt) {
    // TODO: implement the real thing (maybe print the choices and get selection from user)
    return choices.length <= 0 ? null : choices[0];
  }
}
