package com.mycompany.airlinereservation.util;

// thrown when a piece of code should not be reached
public class ShouldNotReachException extends Exception {
    public ShouldNotReachException() {
        super("This code should not be reached");
    }
}
