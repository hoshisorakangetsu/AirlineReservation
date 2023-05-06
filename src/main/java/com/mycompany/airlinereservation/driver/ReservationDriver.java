package com.mycompany.airlinereservation.driver;

import java.util.Date;

import com.mycompany.airlinereservation.entity_classes.PlaneSchedule;
import com.mycompany.airlinereservation.entity_classes.Reservation;

public class ReservationDriver {
    @SuppressWarnings("deprecation")
    private static PlaneSchedule[] schedules = new PlaneSchedule[] {
        new PlaneSchedule(
            30.0, new Date(2023, 5, 15), 
            AirportDriver.getAirports()[0], AirportDriver.getAirports()[4], 
            PlaneDriver.getPlanes()[0], true
        ),
        new PlaneSchedule(
            25.0, new Date(2023, 5, 16), 
            AirportDriver.getAirports()[4], AirportDriver.getAirports()[0], 
            PlaneDriver.getPlanes()[1], true
        ),
        new PlaneSchedule(
            20.0, new Date(2023, 5, 17), 
            AirportDriver.getAirports()[2], AirportDriver.getAirports()[6], 
            PlaneDriver.getPlanes()[2], false
        ),
        new PlaneSchedule(
            15.0, new Date(2023, 5, 18), 
            AirportDriver.getAirports()[3], AirportDriver.getAirports()[1], 
            PlaneDriver.getPlanes()[3], false
        ),
        new PlaneSchedule(
            10.0, new Date(2023, 5, 19), 
            AirportDriver.getAirports()[5], AirportDriver.getAirports()[10], 
            PlaneDriver.getPlanes()[4], true
        ),
    };
    // will be filled in with data later as it relies on customer making a reservation, 
    // init with an empty reservation that can store up to 10 reservations first
    private static Reservation[] reservations = new Reservation[10]; 
}
