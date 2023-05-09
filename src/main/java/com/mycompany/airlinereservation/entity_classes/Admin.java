package com.mycompany.airlinereservation.entity_classes;

import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ChoiceString;

public class Admin extends Account {

    public Admin(String username, String password) {
        super(username, password);
    }

    // should not be used, but is provided
    public Admin() {
        this("", "");
    }

    public static ChoiceString[] getOperations() {
        ChoiceString[] csAdmin = new ChoiceString[] {
            new ChoiceString("Add Airplanes"),
            new ChoiceString("View Airplanes"),
            new ChoiceString("Edit Airplanes"),
            new ChoiceString("Add Airports"),
            new ChoiceString("View Airports"),
            new ChoiceString("Edit Airports"),
            new ChoiceString("Add Plane Schedules"),
            new ChoiceString("View Plane Schedules"),
            new ChoiceString("Edit Plane Schedules"),
            new ChoiceString("Add Customer"),
            new ChoiceString("View Customer"),
            new ChoiceString("Edit Customer"),
            new ChoiceString("Add Admins"),
            new ChoiceString("View Admin"),
            new ChoiceString("Edit Admin"),
        };

        // combine admin's operations and common account operations
        return ArrayUtils.appendArray(csAdmin, Account.getOperations());
    }

    @Override
    public String toString() {
        return super.toString() + "Role       : Admin\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Admin))
            return false;
        return super.equals(o);
    }

}
