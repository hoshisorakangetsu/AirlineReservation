package com.mycompany.airlinereservation.driver;

import java.util.Date;

import com.mycompany.airlinereservation.entity_classes.PlaneSchedule;

public class PlaneScheduleDriver {
    @SuppressWarnings("deprecation")
    private static PlaneSchedule[] schedules = new PlaneSchedule[] {
        new PlaneSchedule(
            30.0, new Date(2023, 5, 15, 10, 0), 
            AirportDriver.getAirport(0), AirportDriver.getAirport(4), 
            PlaneDriver.getPlane(0), false
        ),
        new PlaneSchedule(
            25.0, new Date(2023, 5, 16, 8, 30), 
            AirportDriver.getAirport(4), AirportDriver.getAirport(0), 
            PlaneDriver.getPlane(1), false
        ),
        new PlaneSchedule(
            20.0, new Date(2023, 5, 17, 2, 45), 
            AirportDriver.getAirport(2), AirportDriver.getAirport(6), 
            PlaneDriver.getPlane(5), true
        ),
        new PlaneSchedule(
            15.0, new Date(2023, 5, 21, 19, 30), 
            AirportDriver.getAirport(3), AirportDriver.getAirport(1), 
            PlaneDriver.getPlane(3), false
        ),
        new PlaneSchedule(
            10.0, new Date(2023, 6, 10, 23, 35), 
            AirportDriver.getAirport(5), AirportDriver.getAirport(12), 
            PlaneDriver.getPlane(8), true
        ),
    };
}
