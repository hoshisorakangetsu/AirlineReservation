package com.mycompany.airlinereservation.util;

public class PrettyPrint {
    // only used in here
    // returns the longest string in a String array
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

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static String centerString(String s, int n) {
        int spaceSize = n - s.length();
        int prefixSize = spaceSize / 2;
        int suffixSize = (spaceSize + 1) / 2;

        if (prefixSize != 0 && suffixSize != 0)
           return String.format("%" + prefixSize + "s%s%" + suffixSize + "s", "", s, "");
        else if (prefixSize == 0 && suffixSize != 0)
            return String.format("%s%" + suffixSize + "s", "", s, "");
        else if (prefixSize != 0 && suffixSize == 0)
            return String.format("%" + prefixSize + "s%s", "", s, "");
        else
            return s;
    }

    // print a horizontal line consist of `chars` '='s
    public static void printHorizontalLine(int chars) {
        System.out.println(String.format("%" + chars + "s", " ").replace(" ", "="));
    }

    // prints the options in a table form
    // assumes array passed in is array of objects whose class implements Choicer interface
    public static void printOptions(Choicer[] ops) {
        // get number of elements in string, so can know need to pad how much
        int elNums = String.valueOf(ops.length).length();
        String[] choices = new String[ops.length];
        // call toChoiceString method and make it into a string array for easier work later
        for (int i = 0; i < choices.length; i++) {
            choices[i] = ops[i].toChoiceString();
        }
        String longest = PrettyPrint.longestString(choices);

        // default word heading for elNums is Option, so if elNum is not longer than Option, should pad the numbers based on how long Option is
        if (elNums < "Options".length()) {
            elNums = "Options".length();
        }

        // default word heading for longest is Description, so if longest length is not longer than Description, should pad the longest to the Description length
        if (longest.length() < "Description".length()) {
            longest = "Description";
        }

        // calculate how long should everyline be
        // | elNums | longest |
        // 1 + 1 + elNums + 1 + 1 + 1 + longest.length() + 1 + 1
        int lineLength = 2 + elNums + 3 + longest.length() + 2;
        // the first horizontal line to print the table
        PrettyPrint.printHorizontalLine(lineLength);
        // print heading
        System.out.println(
            "| " + 
            PrettyPrint.padLeft("Options", elNums) + 
            " | " + 
            PrettyPrint.padRight("Description", longest.length()) + 
            " |"
        );
        PrettyPrint.printHorizontalLine(lineLength);

        // since alrd convert to string array above
        for (int i = 0; i < choices.length; i++) {
            // print the number part
            System.out.print("| ");
            System.out.print(PrettyPrint.padLeft(String.valueOf(i + 1), elNums));
            System.out.print(" | ");

            // print the description part
            System.out.print(PrettyPrint.padRight(choices[i], longest.length()));
            System.out.print(" |\n");
        }

        // close the "table"
        PrettyPrint.printHorizontalLine(lineLength);
    }

    // not sure if we will need this
    // prints out the options, before the table, print the heading first
    public static void printOptions(Choicer[] ops, String heading) {
        System.out.println(heading);
        PrettyPrint.printOptions(ops);
    }

    public static void printDetailsCard(Object o) {
        // System.out.println(o.toString());
        // want to format it like a card, with the class name in the center on the header row
        String objClass = o.getClass().getSimpleName();
        String[] properties = o.toString().split("\n");
        String[] getMaxLengthArr = new String[properties.length + 1];
        System.arraycopy(properties, 0, getMaxLengthArr, 0, properties.length);
        getMaxLengthArr[getMaxLengthArr.length - 1] = objClass;
        // find padding for each row
        int sPad = PrettyPrint.longestString(getMaxLengthArr).length();
        int horizontalWidth = sPad + 4; // + '| ' + ' |'

        // header part
        PrettyPrint.printHorizontalLine(horizontalWidth);
        System.out.println("| " + PrettyPrint.centerString(objClass, sPad) + " |");
        PrettyPrint.printHorizontalLine(horizontalWidth);

        // print the contents inside
        for (String s : properties) {
            System.out.println("| " + PrettyPrint.padRight(s, sPad) + " |");
        }
        // close the "card"
        PrettyPrint.printHorizontalLine(horizontalWidth);
    }

    public static void printBanner() {
        String plane = """
             _____
            _\\ _~-\\___
    =  = ==(____AA____D
                \\_____\\___________________,-~~~~~~~`-.._
                /     o O o o o o O O o o o o o o O o  |\\_
                `~-.__        ___..----..                  )
                      `---~~\\___________/------------`````
                      =  ===(_________D   

        """;
        String[] planeStrArr = plane.split("\n");
        int planeLineLength = longestString(planeStrArr).length();

        // top bar
        printHorizontalLine(planeLineLength + 4);  // + "| " + " |"
        // print top padding
        System.out.println("| " + padRight(" ", planeLineLength) + " |");
        // plane itself
        for (String s : planeStrArr) {
            System.out.println("| " + padRight(s, planeLineLength) + " |");
        }
        // add bottom padding for the plane
        System.out.println("| " + padRight(" ", planeLineLength) + " |");
        // show message
        System.out.println("| " + centerString("Welcome to Anirlines Reserveration System", planeLineLength) + " |");
        // add bottom padding for the message
        System.out.println("| " + padRight(" ", planeLineLength) + " |");
        // bottom bar
        printHorizontalLine(planeLineLength + 4);
        System.out.print("\n");
    }

}
