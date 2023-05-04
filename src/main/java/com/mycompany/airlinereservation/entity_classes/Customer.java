package com.mycompany.airlinereservation.entity_classes;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ChoiceString;

public class Customer extends Account {

    private Date dateJoined;

    public Customer(String username, String password, Date dateJoined) {
        super(username, password);
        this.dateJoined = dateJoined;
    }

    // date joined can be assigned by the system, which will be the system time when the customer is created
    public Customer(String username, String password) {
        this(username, password, new Date());
    }

    // should not be used, but is provided
    public Customer() {
        this("", "", new Date());
    }

    public Date getDateJoined() {
        return this.dateJoined;
    }

    public void setDateJoined(Date newDateJoined) {
        this.dateJoined = newDateJoined;
    }

    public static ChoiceString[] getOperations() {
        ChoiceString[] csCust = new ChoiceString[] {
            new ChoiceString("Make reservation"),
            new ChoiceString("View Reservations"),
            new ChoiceString("Edit Reservation"),
        };

        // combine customer's operations with the common account operations
        return ArrayUtils.appendArray(csCust, Account.getOperations());
    }

    @Override
    public String toString() {
        return super.toString() + 
            String.format(
                "Role       : Customer" + 
                "Date Joined: %-20s",
                new SimpleDateFormat("dd/MMM/yyyy").format(this.dateJoined)
            );
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof Customer))
            return false;
        
        return super.equals(obj) && ((Customer) obj).dateJoined.equals(this.dateJoined);
    }
    
}
