package com.mycompany.airlinereservation.entity_classes;

import com.mycompany.airlinereservation.util.ChoiceString;

public abstract class Account {

    private String username;
    // password should not have getter, should not be able to retrieve pw, only
    // override
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // has the option to instantiate empty, but should not be used
    public Account() {
        // default to empty string for the values
        this("", "");
    }

    // getters
    public String getUsername() {
        return this.username;
    }

    // verifies if the user can login
    public boolean verifyCredentials(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // false indicate change password failed
    public boolean changePassword(String oriPassword, String newPassword) {
        if (!oriPassword.equals(this.password))
            return false;

        this.password = newPassword;
        return true;
    }

    // common account operations
    public static ChoiceString[] getOperations() {
        return new ChoiceString[] {
            new ChoiceString("View Account Details"),
            new ChoiceString("Edit username"),
            new ChoiceString("Change Password"),
            new ChoiceString("Log Out"),
        };
    }

    @Override
    public String toString() {
        return String.format(
                "Username   : %-20s\n" +
                "Password   : %-20s\n",
                this.username,
                this.password.replaceAll(".", "*") // replace every character with *
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Account))
            return false;
        return ((Account) obj).username.equals(this.username) && ((Account) obj).password.equals(this.password);
    }

}
