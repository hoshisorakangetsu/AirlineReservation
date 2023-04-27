package com.mycompany.airlinereservation.util;

public class PrettyPrint {
    // only used in here
    // returns the longest string in an array
    private static String longestString(String[] strArr) {
        String currLongest = strArr[0];

        for (int i = 1; i < strArr.length; i++) {
            if (strArr[i].length() > currLongest.length())
                currLongest = strArr[i];
        }
        return currLongest;
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);  
    }

    // print a horizontal line consist of `spaces` '='s
    public static void printHorizontalLine(int spaces) {
        System.out.println(String.format("%" + spaces + "s", " ").replace(" ", "="));
    }

    // prints the options in a table form
    // assumes array passed in is String array
    public static void printOptions(String[] ops) {
        // get number of elements in string, so can know need to pad how much
        int elNums = String.valueOf(ops.length).length();
        for (int i = 0; i < ops.length; i++) {
            // print the number part
            System.out.print("| ");
            System.out.print(PrettyPrint.padLeft(String.valueOf(i + 1), elNums));
            System.out.print(" | ");
        }
    }
}
