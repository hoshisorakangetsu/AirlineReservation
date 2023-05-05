package com.mycompany.airlinereservation.driver;

public class AccountDriver {

    // encapsulate states related to account handling in this class, main program will not have any states
    private static Account[] accounts = new Account[] {
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

    public static void logout() {
        loggedInAccount = null;  // stop pointing to that account, will auto logout, thts the purpose of keeping the state
    }
}
