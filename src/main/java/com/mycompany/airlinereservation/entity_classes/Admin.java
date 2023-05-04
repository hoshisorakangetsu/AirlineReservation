package com.mycompany.airlinereservation.entity_classes;

import com.mycompany.airlinereservation.util.ChoiceString;

public class Admin extends Account {

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin() {
        this("", "");
    }

    public static ChoiceString[] getOperations() {
        return new ChoiceString[] {
            new ChoiceString("Add Airplanes"),
            new ChoiceString("View Airplanes"),
            new ChoiceString("Edit Airplanes"),
            new ChoiceString("Add Airports"),
            new ChoiceString("View Airports"),
            new ChoiceString("Edit Airports"),
            new ChoiceString("Add Flight Schedules"),
            new ChoiceString("View Flight Schedules"),
            new ChoiceString("Edit Flight Schedules"),
            new ChoiceString("View Account Details"),
            new ChoiceString("Add Customers"),
            new ChoiceString("View Customers"),
            new ChoiceString("Edit Username"),
            new ChoiceString("Change Password"),
            new ChoiceString("Log Out"),
        };
    }

    @Override
    public String toString() {
        return super.toString() + "Role    : Admin\n";
    }

}
