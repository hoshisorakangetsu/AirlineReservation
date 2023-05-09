package com.mycompany.airlinereservation.entity_classes;

import com.mycompany.airlinereservation.util.ChoiceString;
import com.mycompany.airlinereservation.util.Choicer;

// even though dh abstract method, objects of this class should not be created, so mark this class as abstract
public abstract class Account implements Choicer {

    private String username;
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

    // setters
    public void setUsername(String username) {
        this.username = username;
    }

    // should not be called directlt, except is admin, but in here we can't check the caller
    public void setPassword(String password) {
        this.password = password;
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

    // returns the list, in ChoiceString of possible actions that can be carried out before login
    public static ChoiceString[] getBeforeLoginOptions() {
        return new ChoiceString[] {
            new ChoiceString("Login"),
            new ChoiceString("Register new account"),
            new ChoiceString("Exit system"),
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

    @Override
    public String toChoiceString() {
        // we can identify an account via its username only as it will be unique
        return this.username;
    }

}
