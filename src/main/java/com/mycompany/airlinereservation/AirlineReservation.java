package com.mycompany.airlinereservation;

import java.util.Date;

import com.mycompany.airlinereservation.driver.*;
// every class in entity_classes would be used
import com.mycompany.airlinereservation.entity_classes.Account;
import com.mycompany.airlinereservation.entity_classes.Admin;
import com.mycompany.airlinereservation.entity_classes.Customer;
import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ChoiceString;
import com.mycompany.airlinereservation.util.ConsoleInput;
import com.mycompany.airlinereservation.util.PrettyPrint;
import com.mycompany.airlinereservation.util.ShouldNotReachException;

public class AirlineReservation {

    public static void main(String[] args) {
        // Plane[] planes = new Plane[];
        // PlaneSchedule[] sched = new PlaneSchedule[];
        // Reservation[] reserves = new Reservation[];
        // Payment[] payments = new Payments[];
        // Reservation[] reservations = new Reservation[];
        // Payment[] payments = new Payment[];

        while (true) {
            // flow
            // check if user is logged in, if not logged in, 
            // ask user 1. login, 2. register 3. exit system
            if (!AccountDriver.isLoggedIn()) {
                int beforeLoginSelection = ConsoleInput.getChoice(
                    Account.getBeforeLoginOptions(), 
                    "Please enter your choice: "
                );
                ConsoleInput.clearBuffer();


                // if is not exit, directly continue from the while loop
                // the state kept on AccountDriver will let this block not execute next time
                // if did not continue, still need check is logged in ltr, redundant operation
                if (beforeLoginSelection != 3) {
                    AccountDriver.executeOperation(beforeLoginSelection);
                    continue;
                } else {
                    break;  // exit from the loop, technically exits the system as well as there are no more code underneath the big while true loop
                }
            }

            // if this is reachable then the account should be logged in
            // flow: show user's menu
            int menuChoice;
            try {
                menuChoice = ConsoleInput.getChoice(
                    AccountDriver.getMenu(),
                    "Please enter your action: "
                );
            } catch (ShouldNotReachException snre) {
                System.out.println(snre.getMessage());
                continue; // if ShouldNotReachExceptionis thrown, 
            }
            // if err is catched this code will be skipped, so it's safe to assume when the code reaches here, menuChoice will alrd have a value
            // admin driver handles 10 - 15, main handles 16 - 20 by calling the methods provided by AccountDriver, avoiding directly affecting the state of the AccountDriver
            if (AccountDriver.getLoggedInAccount() instanceof Admin) {
                // 10 - 15 handled by AdminDriver
                if (menuChoice >= 10 && menuChoice <= 15) {
                    AccountDriver.AdminDriver.executeOperation(menuChoice);
                    continue;
                }
                // 16 - 19 handled by AccountDriver
                if (menuChoice >= 16 && menuChoice <= 19) {
                    switch (menuChoice) {
                        case 16:
                            AccountDriver.viewAccountDetails();
                            break;
                        case 17:
                            AccountDriver.changeUsername();
                            break;
                        case 18:
                            AccountDriver.changePassword();
                            break;
                        case 19:
                            AccountDriver.logout();
                            break;
                    
                        // should not trigger
                        default:
                            break;
                    }
                    // done operation, skip to the next iteration of the big while loop to trigger the menu again, let it be login menu (if user logs out) or the normal menu
                    continue;
                }
            } else if (AccountDriver.getLoggedInAccount() instanceof Customer) {
                // 1 - 3 handled by ReservationDriver
                // 4 - 7 handled by AccountDriver
                if (menuChoice >= 4 && menuChoice <= 7) {
                    switch (menuChoice) {
                        case 4:
                            AccountDriver.viewAccountDetails();
                            break;
                        case 5:
                            AccountDriver.changeUsername();
                            break;
                        case 6:
                            AccountDriver.changePassword();
                            break;
                        case 7:
                            AccountDriver.logout();
                            break;
                    
                        // should not trigger
                        default:
                            break;
                    }
                    // done operation, skip to the next iteration of the big while loop to trigger the menu again, let it be login menu (if user logs out) or the normal menu
                    continue;
                }
            }
            break; // break for now to prevent infinite loop as bottom implementation havent done
        }
        
        // while (true) {
        //     // if there isn't any logged in account
        //     if (loggedInAccount == null) {
        //         int beforeLoginChoice = ConsoleInput.getChoice(Account.getBeforeLoginOptions(), "Please enter your choice: ");
        //         if (beforeLoginChoice == 3) return;  // yes exit the whole system ;)
        //     }

        //     // show different menu and carry out different actions depending on the logged in user
        //     if (loggedInAccount instanceof Customer) {
        //         int customerChoice = ConsoleInput.getChoice(Customer.getOperations(), "Please enter your choice: ");
        //     } else if (loggedInAccount instanceof Admin) {
        //         int adminChoice = ConsoleInput.getChoice(Admin.getOperations(), "Please enter your choice: ");
        //     }
        // }
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

}
