package com.mycompany.airlinereservation.driver;

import java.util.Arrays;
import java.util.Date;

import com.mycompany.airlinereservation.entity_classes.Account;
import com.mycompany.airlinereservation.entity_classes.Admin;
import com.mycompany.airlinereservation.entity_classes.Customer;
import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ConsoleInput;
import com.mycompany.airlinereservation.util.ShouldNotReachException;

public class AccountDriver {

    // encapsulate states related to account handling in this class, main program will not have any states
    @SuppressWarnings("deprecation")
    private static Account[] userAccounts = new Account[] {
        new Customer("JohnDoe", "securePassword", new Date(2000, 10, 3)),
        new Admin("admin", "root"),
    };
    private static Account loggedInAccount = null;

    // if the loggedInAccount is assigned, definitely there will be someone who already logged in
    public static boolean isLoggedIn() {
        return loggedInAccount != null;
    }

    // the whole flow to login an account
    public static void login() {
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

    public static void register() {
        while (true) {
            try {
                String username = ConsoleInput.getString("Enter a username: ");
                // check if username is alrd occupied
                for (Account a : userAccounts) {
                    if (a.getUsername().equals(username)) {
                        throw new AccountRegisteredException(username);
                    }
                }
                String password, reenterPassword;
                do {
                    password = ConsoleInput.getString("Please enter password: ");
                    reenterPassword = ConsoleInput.getString("Please reenter password: ");
    
                    if (!password.equals(reenterPassword)) {
                        System.out.println("Two passwords are different, please check");
                    }
                } while (!password.equals(reenterPassword));
    
                // register a customer account for the user, admin accounts should never be able to be registered on its own
                Customer newCust = new Customer(username, password);
                userAccounts = ArrayUtils.appendIntoArray(userAccounts, newCust);
                return;  // done registering, return from the function

            } catch (AccountRegisteredException accRegE) {
                System.out.println(accRegE.getMessage());
                System.out.println("Please select another one");
            }
        }
    }

    public static void getMenu() {
        if (loggedInAccount instanceof Admin) {
            Admin.getOperations();
        } else if (loggedInAccount instanceof Customer) {
            Customer.getOperations();
        }
    }

    public static void executeOperation(int choice) {
        if (choice == 1) {
            login();
        } else if (choice == 2) {
            register();
        }
    }

    public static void logout() {
        loggedInAccount = null;  // stop pointing to that account, will auto logout, thts the purpose of keeping the state
    }

    // nested class, as Admin is one of Account as well, so placing the driver here makes sense
    public static class AdminDriver {

    }

    // nested class, as Customer is one of Account as well, so placing the driver here makes sense
    public static class CustomerDriver {

    }
}

// only used in this file, used to continue the while loop of ask user get details if the username has been taken
class AccountRegisteredException extends Exception {
    AccountRegisteredException(String username) {
        super("Username '" + username + "' has been taken");
    }
}
