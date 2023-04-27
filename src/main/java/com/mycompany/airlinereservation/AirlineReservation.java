package com.mycompany.airlinereservation;

import com.mycompany.airlinereservation.util.*;

public class AirlineReservation {

    public static void main(String[] args) {
        // User[] users = new User[];
        // Plane[] planes = new Plane[];
        // PlaneSchedule[] sched = new PlaneSchedule[];
        // Reservation[] reserves = new Reservation[];
        // Payment[] payments = new Payments[];
        // Reservation[] reservations = new Reservation[];
        // Payment[] payments = new Payment[];

        // flow
        // login
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
        
        System.out.println("Hello World!");
        String s = ConsoleInput.getString("Test, string please?: ");
        PrettyPrint.printHorizontalLine(20);
        System.out.println(s);
    }
}
