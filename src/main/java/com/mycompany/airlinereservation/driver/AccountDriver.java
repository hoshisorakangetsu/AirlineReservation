package com.mycompany.airlinereservation.driver;

import java.util.Date;

import com.mycompany.airlinereservation.entity_classes.Account;
import com.mycompany.airlinereservation.entity_classes.Admin;
import com.mycompany.airlinereservation.entity_classes.Customer;
import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ChoiceString;
import com.mycompany.airlinereservation.util.Choicer;
import com.mycompany.airlinereservation.util.ConsoleInput;
import com.mycompany.airlinereservation.util.PrettyPrint;
import com.mycompany.airlinereservation.util.ShouldNotReachException;

public class AccountDriver {

    // encapsulate states related to account handling in this class, main program will not have any states
    @SuppressWarnings("deprecation")
    private static Account[] userAccounts = {
        new Customer("JohnDoe", "securePassword", new Date(2000, 10, 3)),
        new Admin("admin", "root"),
    };
    private static Account loggedInAccount = null;

    // if the loggedInAccount is assigned, definitely there will be someone who already logged in
    public static boolean isLoggedIn() {
        return loggedInAccount != null;
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
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
    
    public static ChoiceString[] getMenu() throws ShouldNotReachException {
        if (loggedInAccount instanceof Admin) {
            return Admin.getOperations();
        } else if (loggedInAccount instanceof Customer) {
            return Customer.getOperations();
        }
        
        throw new ShouldNotReachException();
    }
    
    public static void changePassword() {
        String oriPassword, newPassword;
        do {
            oriPassword = ConsoleInput.getString("Enter original password: ");
            newPassword = ConsoleInput.getString("Enter new password: ");
            // loop until change password successfully
        } while (!loggedInAccount.changePassword(oriPassword, newPassword));
        System.out.println("Password changed successfully!");
    }

    public static void viewAccountDetails() {
        PrettyPrint.printDetailsCard(loggedInAccount);
    }

    public static void changeUsername() {
        String newUsername = ConsoleInput.getString("Enter your desired new username: ");
        loggedInAccount.setUsername(newUsername);
        System.out.println("New username '" + loggedInAccount.getUsername() + "' successfully set!");
    }
    
    // handles login and register only
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

    public static Choicer[] getAccounts(String filter) {
        // performance isn't concerned, just create an array with length 1 (there will always be at least one customer or admin) and keep inserting into it
        Choicer[] res = new Choicer[1];

        for (Account a : userAccounts) {
            if (a instanceof Customer && filter.equals("CUSTOMER")) {
                res = ArrayUtils.appendIntoArray(res, a);
            } else if (a instanceof Admin && filter.equals("ADMIN")) {
                res = ArrayUtils.appendIntoArray(res, a);
            }
        }

        return res;
    }

    // nested class, as Admin is one of Account as well, so placing the driver here makes sense
    // but it only handles operations like customer details and add new admin or edit admins
    // operations like edit flight or add flight will be handled by respective driver classes
    public static class AdminDriver {

        public static void addAccount(String type) {
            enterUsername: // label to quit and ask admin to put another username in case username crashed
            while (true) {
                String username = ConsoleInput.getString("Enter new username: ");
                for (Account a : userAccounts) {
                    if (a.getUsername().equals(username)) {
                        System.out.println("Username repeated, please select another one");
                        continue enterUsername;
                    }
                }

                String password = ConsoleInput.getString("Enter a password: ");

                // create account
                Account newAcc = null;
                if (type.equals("CUSTOMER")) {
                    newAcc = new Customer(username, password);
                } else if (type.equals("ADMIN")) {
                    newAcc = new Admin(username, password);
                }
                userAccounts = ArrayUtils.appendIntoArray(userAccounts, newAcc);

                // finished business, exit from this function and return to main flow
                return;
            }
        }

        public static void viewAccount(String type) {
            // View Customer
            // flow: show customers in a list of choices, ask which customer to view in detail
            Choicer[] accs = AccountDriver.getAccounts(type);
            int accSelected = ConsoleInput.getChoice(
                accs, 
                String.format("Which %s to view in detail: ", type.toLowerCase())
            );
            ConsoleInput.reInit();
            PrettyPrint.printDetailsCard(accs[accSelected - 1]);
            // block until user decides everything is ok
            ConsoleInput.getString("Press [enter] to continue");
        }

        public static void editAccount(String type) {
            // flow: show customers in a list of choices, ask which customer to edit
            Choicer[] accs = AccountDriver.getAccounts(type);
            int accSelected = ConsoleInput.getChoice(
                accs, 
                String.format("Which %s to edit: ", type)
            );
            ConsoleInput.reInit();
            // performance isn't concerned, find the original Customer from userAccounts
            // if not found (which shudn't be, just exit as not possible to continue)
            int accIndex = ArrayUtils.indexOf(userAccounts, accs[accSelected - 1]);
            if (accIndex < 0 || accIndex > userAccounts.length) {
                System.out.println("Account not found");
                return;
            }
            Account account = userAccounts[accIndex];
            String newUsername;
            usernameRepeat:
            while (true) {
                newUsername = ConsoleInput.getString("Enter new username (leave blank to not change): ");
                if (newUsername.isBlank()) {
                    break;
                }
                for (Account a : userAccounts) {
                    if (a.getUsername().equals(newUsername)) {
                        System.out.println("Duplicate username, please select another one");
                        continue usernameRepeat;
                    }
                }
                break;
            }
            if (!newUsername.isBlank()) {
                account.setUsername(newUsername);
            }
            String newPassword = ConsoleInput.getString("Enter new password (leave blank to not change): ");
            if (!newPassword.isBlank()) {
                account.setPassword(newPassword);
            }
            System.out.printf("%s edited successfully!\n", type);
        }
        
        public static void executeOperation(int ops) {
            switch (ops) {
                // add customer and admin got similar flow at first, so can grup them tgt
                // 10 - Add Customer, 13 - Add Admin
                case 10:
                case 13:
                    if (ops == 10) {
                        addAccount("CUSTOMER");
                    } else if (ops == 13) {
                        addAccount("ADMIN");
                    }
                    // finish business, exit function
                    break;

                // 11 - view customer, 14 - view admin
                case 11: 
                case 14: 
                    viewAccount(ops == 11 ? "CUSTOMER" : "ADMIN");
                    break;                    
                // 12 - Edit customer, 15 - edit admin, they both got same implementation
                case 12:
                case 15: 
                    editAccount(ops == 12 ? "CUSTOMER" : "ADMIN");
                    break;
                    
                // should not reach
                default:
                    break;
            }
        }

    }

}

// only used in this file, used to continue the while loop of ask user get details if the username has been taken
class AccountRegisteredException extends Exception {
    AccountRegisteredException(String username) {
        super("Username '" + username + "' has been taken");
    }
}
