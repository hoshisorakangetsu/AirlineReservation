package com.mycompany.airlinereservation;

import java.util.Date;

// every class in entity_classes would be used
import com.mycompany.airlinereservation.entity_classes.*;
import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ChoiceString;
import com.mycompany.airlinereservation.util.ConsoleInput;
import com.mycompany.airlinereservation.util.PrettyPrint;

public class AirlineReservation {
    
    // these are common states that all the flows share, in an OOP context, they should not be defined in the main function and passed around, that is more like functional approach
    // since OOP allows for encapsulation, these aren't really count as Global Variables, as these are still only accessible in this class
    // default we will have one customer and one admin, we will allow more customer to be registered
    // this date constructor is deprecated but achieves what we want easily, so we will keep using it for now
    @SuppressWarnings("deprecation")
    private static Account[] userAccounts = new Account[] {
        new Customer("JohnDoe", "securePassword", new Date(2000, 10, 3)),
        new Admin("admin", "root"),
    };

    // keeps track of the logged in user
    private static Account loggedInAccount;

    public static void main(String[] args) {
        // Plane[] planes = new Plane[];
        // PlaneSchedule[] sched = new PlaneSchedule[];
        // Reservation[] reserves = new Reservation[];
        // Payment[] payments = new Payments[];
        // Reservation[] reservations = new Reservation[];
        // Payment[] payments = new Payment[];

        // flow
        // ask user 1. login, 2. register 3. exit system
        while (true) {
            // if there isn't any logged in account
            if (loggedInAccount == null) {
                int beforeLoginChoice = ConsoleInput.getChoice(Account.getBeforeLoginOptions(), "Please enter your choice: ");
                if (beforeLoginChoice == 3) return;  // yes exit the whole system ;)
            }

            // show different menu and carry out different actions depending on the logged in user
            if (loggedInAccount instanceof Customer) {
                int customerChoice = ConsoleInput.getChoice(Customer.getOperations(), "Please enter your choice: ");
            } else if (loggedInAccount instanceof Admin) {
                int adminChoice = ConsoleInput.getChoice(Admin.getOperations(), "Please enter your choice: ");
            }
        }
        // if customer
        //      create reservation
        //          select plane sched
        //              fill in number of passengers
        //              fill in passenger details
        //              select seat
        //              create ticket
        //          repeat until all passenger fill in complete
        //          select payment option
        //              make payment
        //      check reservation
        //          print all reservations
        //          ask wan check which reservation
        //              print all the tickets
        //              ask wan check which ticket
        //                  ask if want to edit
        //                      edit name
        //                      edit ID number
        //                      if econ, ask if wan Upgrade()
        // elsif admin
        //      view plane schedules
        //          ask which schedule
        //          display schedule
        //          ask if wan edit schedules
        //              edit price
        //              show available planes
        //              edit plane
        //              delay flight?
        //          add plane schedule
        //      view planes
        //          add planes
        //      view users
        //          add customers
        //          add users
        // else 
        //      show error
        //      ask if wan register
        //          get username
        //          get password
        
        // System.out.println("Hello World!");
        // String s = ConsoleInput.getString("Test, string please?: ");
        // PrettyPrint.printHorizontalLine(20);
        // System.out.println(s);
    }

    // the whole flow to login an account
    static void login() {
        // yes keep asking, if the credentials matched or user dw to try again, the function will return and exit
        while (true) {
            // ask for username and password
            String username = ConsoleInput.getString("Please enter username: ");
            String password = ConsoleInput.getString("Please enter password: ");
    
            // check if any accounts matched
            for (Account a : userAccounts) {
                if (a.verifyCredentials(username, password)) {
                    loggedInAccount = a;
                    return;
                }
            }

            // return from function if user dw to try again
            if(
                Character.toLowerCase(
                    ConsoleInput.getChar("Invalid credentials, try again? [Y/n]: ")
                ) != 'y'
            ) return;
        }
    }

    static void register() {
        contForReregister:  // label used to make the whole process start again when username is occupied
        while (true) {
            String username = ConsoleInput.getString("Enter a username: ");
            // check if username is alrd occupied
            for (Account a : userAccounts) {
                if (a.getUsername().equals(username)) {
                    System.out.println("Username has already been taken, please select another one");
                    continue contForReregister;
                }
            }
            String password, reenterPassword;
            do {
                password = ConsoleInput.getString("Please enter password: ");
                reenterPassword = ConsoleInput.getString("Please reenter password: ");

                if (password != reenterPassword) {
                    System.out.println("Two passwords are different, please check");
                }
            } while (password != reenterPassword);

            // register a customer account for the user, admin accounts should never be able to be registered on its own
            Customer newCust = new Customer(username, password);
            ArrayUtils.appendIntoArray(userAccounts, newCust);
            return;  // done registering, return from the function
        }
    }

    static void logout() {
        loggedInAccount = null;  // stop pointing to that account, will auto logout, thts the purpose of keeping the state
    }
}
