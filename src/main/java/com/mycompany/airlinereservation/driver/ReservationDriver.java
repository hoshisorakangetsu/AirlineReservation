package com.mycompany.airlinereservation.driver;

import java.util.Date;

import com.mycompany.airlinereservation.entity_classes.Account;
import com.mycompany.airlinereservation.entity_classes.Card;
import com.mycompany.airlinereservation.entity_classes.Cash;
import com.mycompany.airlinereservation.entity_classes.Customer;
import com.mycompany.airlinereservation.entity_classes.Domestic;
import com.mycompany.airlinereservation.entity_classes.EWallet;
import com.mycompany.airlinereservation.entity_classes.International;
import com.mycompany.airlinereservation.entity_classes.Payment;
import com.mycompany.airlinereservation.entity_classes.PlaneSchedule;
import com.mycompany.airlinereservation.entity_classes.PlaneTicket;
import com.mycompany.airlinereservation.entity_classes.Reservation;
import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ChoiceString;
import com.mycompany.airlinereservation.util.ConsoleInput;
import com.mycompany.airlinereservation.util.PrettyPrint;
import com.mycompany.airlinereservation.util.ShouldNotReachException;

public class ReservationDriver {

    // will be filled in with data later as it relies on customer making a reservation, 
    // init with an empty reservation that can store up to 10 reservations first
    private static Reservation[] reservations = new Reservation[10]; 

    public static Reservation[] getReservations() {
        return reservations;
    }

    // for the moment this method only used in this driver
    private static Reservation[] getReservationByCustomer(Customer c) {
        Reservation[] custRes = new Reservation[1];

        for (Reservation r : reservations) {
            if (r != null && r.getCust().equals(c)) {
                custRes = ArrayUtils.appendIntoArray(custRes, r);
            }
        }

        // there are possibility that the customer did not make any reservation yet
        if ((custRes.length == 1 && custRes[0] == null)) {
            return null;
        }

        return custRes;
    }

    // only used in this driver for logic separation
    private static Payment getPayment(double totalAmount) throws ShouldNotReachException {
        // show user the total amount and ask user how they want to pay
        String paymentMethod;
        do {
            System.out.printf("The total amount is: RM%.2f\n", totalAmount);
            paymentMethod = ConsoleInput.getString("Please enter desired payment method [CASH, CARD, EWALLET]: ", false);
            if (
                !paymentMethod.toLowerCase().equals("cash") &&
                !paymentMethod.toLowerCase().equals("card") && 
                !paymentMethod.toLowerCase().equals("ewallet")
            )
                System.out.println("Invalid payment option, valid: [CASH, CARD, EWALLET] only");
        } while (
            !paymentMethod.toLowerCase().equals("cash") &&
            !paymentMethod.toLowerCase().equals("card") && 
            !paymentMethod.toLowerCase().equals("ewallet")
        );

        Payment pt = null;

        switch (paymentMethod.toLowerCase()) {
            case "cash":
                pt = new Cash(totalAmount);
                break;
            case "card":
                String cardNo = ConsoleInput.getString("Enter card number: ", false);
                pt = new Card(totalAmount, cardNo);
                break;
            case "ewallet":
                String mobileNo = ConsoleInput.getString("Enter mobile number: ", false);
                String platform = ConsoleInput.getString("Enter platform: ", false);
                pt = new EWallet(totalAmount, mobileNo, platform);
                break;
            default:
                throw new ShouldNotReachException();
        }

        boolean payNow = Character.toLowerCase(
            ConsoleInput.getChar("Pay now? [y/n]: ")
        ) == 'y';

        if (payNow) {
            makePayment(pt);
        }

        return pt;
    }

    // check if the payment stuff, correct, if false, allow them to change their stuffs
    private static void makePayment(Payment pay) {
        if (pay.makePayment()) {
            // if successful, set paymentDate to today and status to true
            System.out.println("Payment successful!");
            pay.setPaymentDate(new Date());
            pay.setPaidStatus(true);
        }
        else {
            System.out.println("Payment unsuccessful");
            // only card and ewallet will fail
            if (pay instanceof Card) {
                boolean retry = Character.toLowerCase(
                    ConsoleInput.getChar("Card is invalid, do you want to change card and retry? [y/n]: ")
                ) == 'y';
                if (retry) {
                    String newCardNo = ConsoleInput.getString("Enter a card number: ", false);
                    ((Card) pay).setCardNo(newCardNo);
                    makePayment(pay); // use the power of recursion, nonid to write loop agn yassssss
                }
            } else if (pay instanceof EWallet) {
                boolean retry = Character.toLowerCase(
                    ConsoleInput.getChar("Mobile Number is invalid, do you want to change card and retry? [y/n]: ")
                ) == 'y';
                if (retry) {
                    String newMobileNo = ConsoleInput.getString("Enter a mobile number: ", false);
                    String platform = ConsoleInput.getString("Enter a platform (leave empty to not change): ");
                    ((EWallet) pay).setMobileNo(newMobileNo);
                    if (!platform.isBlank())
                        ((EWallet) pay).setWalletType(platform);
                    makePayment(pay); // use the power of recursion, nonid to write loop agn yassssss
                }
            }
        }
    }

    // shudn't got problem cuz it will only be called once confirm is Customer
    public static void makeReservation() throws NoAccessException {
        // can get the customer (which is currently logged in) from AccountDriver
        Account currentUser = AccountDriver.getLoggedInAccount();
        // very unlikely to happen
        if (!(currentUser instanceof Customer))
            throw new NoAccessException(currentUser);
        
        // here we can already be sure currentUser is Customer
        Customer currentCustomer = (Customer) currentUser;
        // make reservations lo
        // ask which planeSchedule first
        int planeSchedChoice = ConsoleInput.getChoice(
            PlaneScheduleDriver.getSchedules(), 
            "Which plane schedule would you like to book: "
        );
        // can determine whether is domestic or 
        PlaneSchedule selectedSchedule = PlaneScheduleDriver.getSchedule(planeSchedChoice - 1);
        if (selectedSchedule.getSeatsAvailable() < 1) {
            System.out.println("This schedule has been fully booked, sorry!");
            return;
        }
        // check if schedule is domestic or international
        // if not domestic then confirm international
        boolean isDomestic = selectedSchedule.getSrc().isSameCountry(selectedSchedule.getDest());
        // ask how many passengers (create the same number of ticket for it)
        // Not urgent, time's running out, if got time: check if the number do not exceed the plane's capacity - reservation's ticket number
        int passengerNum = ConsoleInput.getInt("How many passengers: ", 1, selectedSchedule.getSeatsAvailable());
        ConsoleInput.reInit();

        // not separating this chunk of code out because can boost performance (use one for loop to perform create tickets and add the sum of the price at once)
        PlaneTicket[] tickets;
        if (isDomestic) {
            tickets = new Domestic[passengerNum];
        } else {
            tickets = new International[passengerNum];
        }

        // store the total amount
        double totalAmount = 0;

        // get informations and create the tickets
        for (int i = 0; i < passengerNum; i++) {
            // repeatedly get information of the passenger until verify successes
            PlaneTicket pt;
            String passengerName = ConsoleInput.getString("Enter passenger name: ", false);
            int passengerAge = ConsoleInput.getInt("Enter passenger age: ", 1, 130);
            ConsoleInput.reInit();
            double price = selectedSchedule.getBasePrice();
            boolean isBusinessClass = Character.toLowerCase(
                ConsoleInput.getChar("Upgrade to business class (add 40% to the base ticket price)? [y/n]: ")
            ) == 'y';
            String seatType = "ECONOMY";
            if (isBusinessClass) {
                price *= 1.4;
                seatType = "BUSINESS";
            }
            // accumulate the amount
            totalAmount += price;
            do {
                String passengerPassport = ConsoleInput.getString("Enter passenger passport: ");
                if (isDomestic) {
                    // ask for IC and passport, for domestic, one of it can be empty
                    String passengerIC = ConsoleInput.getString("Enter passenger IC: ");
                    pt = new Domestic(selectedSchedule, seatType, passengerName, passengerAge, price, passengerIC, passengerPassport);
                } else {
                    String passengerVisa = null;
                    if (selectedSchedule.getVisaRequired()) {
                        passengerVisa = ConsoleInput.getString("Enter a passenger VISA: ");
                    }
                    pt = new International(selectedSchedule, seatType, passengerName, passengerAge, price, passengerPassport, passengerVisa);
                }
            } while(!pt.verifyDocuments());
            tickets[i] = pt;
        }

        Payment p;
        try {
            p = getPayment(totalAmount);
        } catch (ShouldNotReachException snre) {
            System.out.println("Failed to get payment for this reservation, reservation cancelled");
            return;
        }

        // yes finally can create the reservation object
        Reservation newReservation = new Reservation(tickets, p, currentCustomer);
        reservations = ArrayUtils.appendIntoArray(reservations, newReservation);
        // reduce the number of seats available by the number of tickets booked
        selectedSchedule.bookTickets(passengerNum);
        System.out.println("Reservation successfully made!");
    }

    public static void viewReservationCust() throws NoAccessException {
        Account currentUser = AccountDriver.getLoggedInAccount();
        // very unlikely to happen
        if (!(currentUser instanceof Customer))
            throw new NoAccessException(currentUser);
        
        // here we can already be sure currentUser is Customer
        Customer currentCustomer = (Customer) currentUser;

        Reservation[] resMadeByCust = getReservationByCustomer(currentCustomer);
        if (resMadeByCust == null) {
            System.out.println("You haven't made any reservations yet, please make one before proceeding");
            return;
        }
        int choice = ConsoleInput.getChoice(resMadeByCust, "Which reservation to view: ");

        PrettyPrint.printDetailsCard(resMadeByCust[choice - 1]);
        ConsoleInput.reInit();
        // block until user decides everything is ok
        ConsoleInput.getString("Press [enter] to continue");
    }

    public static void editReservation() throws NoAccessException {
        Account currentUser = AccountDriver.getLoggedInAccount();
        // very unlikely to happen
        if (!(currentUser instanceof Customer))
            throw new NoAccessException(currentUser);
        
        // here we can already be sure currentUser is Customer
        Customer currentCustomer = (Customer) currentUser;

        // show the reservation, ask wan edit which reservation
        Reservation[] resMadeByCust = getReservationByCustomer(currentCustomer);
        if (resMadeByCust == null) {
            System.out.println("You haven't made any reservations yet, please make one before proceeding");
            return;
        }
        int choice = ConsoleInput.getChoice(resMadeByCust, "Which reservation to edit: ");
        Reservation resToEdit = resMadeByCust[choice - 1];  // made possible by the power of java reference, <333
        
        // 2 thing, edit ticket, or make payment, if the payment has been made, directly show the tickets and only let them edit tickets
        if (!resToEdit.getPay().getPaidStatus()) {
            ChoiceString[] choices = new ChoiceString[] {
                new ChoiceString("Edit ticket"),
                new ChoiceString("Make Payment"),
            };
            int choice2 = ConsoleInput.getChoice(choices, "Enter your choice: ");
            ConsoleInput.reInit();
            if (choice2 == 2){
                makePayment(resToEdit.getPay());  // made possible by java reference <33333333
                return;
            }
        }

        // display all the tickets in the reservation
        PlaneTicket[] tickets = resToEdit.getTickets();
        Choicer[] selections = ArrayUtils.appendIntoArray(tickets, new ChoiceString("Done editing"));
        int ticketToEditIdx = ConsoleInput.getChoice(selections, "Which ticket to edit: ");
        ConsoleInput.reInit();
        while (ticketToEditIdx != selections.length) {
            // deduct the price from the payment first, in case user want to upgrade ticket
            // this way of handling is better than the other way which requires to loop over the array agn to add the total
            resToEdit.getPay().setAmount(
                resToEdit.getPay().getAmount() - tickets[ticketToEditIdx - 1].getPrice()
            );
            editTicket(tickets[ticketToEditIdx - 1]);  // <3 u java references
            // add back the price
            resToEdit.getPay().setAmount(
                resToEdit.getPay().getAmount() + tickets[ticketToEditIdx - 1].getPrice()
            );
            ticketToEditIdx = ConsoleInput.getChoice(selections, "Which ticket to edit: ");
            ConsoleInput.reInit();
        }

        System.out.println("Reservation edited successfully");
    }

    public static void editTicket(PlaneTicket pt) {
        // ask everything haha
        String passengerName = ConsoleInput.getString("Enter passenger name (leave empty to not change): ");
        int passengerAge = ConsoleInput.getInt(
            String.format("Enter passenger age (Current: %d): ", pt.getPassengerAge()),
            1, 130
        );
        ConsoleInput.reInit();

        if (!passengerName.isBlank())
            pt.setPassengerName(passengerName);
        pt.setPassengerAge(passengerAge);

        System.out.println("You will be charged if you were to change the following documents");
        boolean hasChanged = false;
        do {
            String passport = ConsoleInput.getString("Enter passport(leave blank to not change): ");
            if (!passport.isBlank()) {
                pt.setPassengerPassport(passport);
                hasChanged = true;
            }
            if (pt instanceof Domestic) {
                String ic = ConsoleInput.getString("Enter IC (leave blank to not change): ");
                if (ic.isBlank())
                    break;
                // if this code was executed means either ic or passport or both is not blank
                hasChanged = true;
                if (!ic.isBlank())
                    ((Domestic) pt).setPassengerIC(ic);
            } else if (pt instanceof International) {
                String visa = ConsoleInput.getString("Enter VISA (leave blank to not change): ");
                if (visa.isBlank())
                    break;
                // if this code was executed means either ic or passport or both is not blank
                hasChanged = true;
                if (!visa.isBlank())
                    ((International) pt).setPassengerVisa(visa);
            }
        } while (!pt.verifyDocuments());
        // business rule: if previous is paid using card or ewallet, prompt user that their balance will be deducted if they are to be charged, if is paid via cash, tell them to pay on the spot
        if (hasChanged) {
            System.out.printf(
                "You have made changes to the ticket that is equivalent of an ownership transfer, please pay RM%.2f, \nIf you paid with EWallet or Card previously, please disregard this message as your balance will be automatically deducted\nThanks for your understanding\n",
                pt.getPrice() * 0.8
            );
        }

        if (pt.getSeatType().equals("ECONOMY")) {
            boolean upgrade = Character.toLowerCase(
                ConsoleInput.getChar("Do you want to upgrade this ticket to business class? [y/n]: ")
            ) == 'n';
            if (upgrade) {
                double oldPrice = pt.getPrice();
                if (pt.upgrade()) {
                    System.out.println("Ticket upgraded to business class");
                    System.out.printf(
                        "Please pay additional RM%.2f\nIf you paid with EWallet or Card previously, please disregard this message as your balance will be automatically deducted\nThanks for your understanding\n", 
                        oldPrice * 0.4
                    );
                }
            }
        }

        System.out.println("Ticket edited successfully");
    }

    // lazy make another file for this, this exception only will be thrown in this class anyways
    public static class NoAccessException extends Exception {
        public NoAccessException(Account a) {
            super("Account " + a.getUsername() + " of role " + a.getClass().getSimpleName() + " have no access over this function");
        }
    }

}
