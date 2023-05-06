package com.mycompany.airlinereservation.driver;

import com.mycompany.airlinereservation.entity_classes.Plane;

// stores all available planes and implements plane related entrypoints
public class PlaneDriver {
    private static Plane[] planes = new Plane[] {
        new Plane(220, "A321", "Airbus", "Malaysia Airlines", "MH321-01"),
        new Plane(853, "A380", "Airbus", "Singapore Airlines", "SQ380-02"),
        new Plane(440, "A310", "Airbus", "China Eastern Airlines", "MU310-03"),
        new Plane(440, "A330", "Airbus", "Malaysia Airlines", "MH330-04"),
        new Plane(440, "A350", "Airbus", "Singapore Airlines", "SQ350-05"),
        new Plane(375, "767", "Boeing", "Malaysia Airlines", "MH767-06"),
        new Plane(200, "757", "Boeing", "China Southern Airlines", "CZ757-07"),
        new Plane(440, "777", "Boeing", "Singapore Airlines", "SQ777-08"),
        new Plane(330, "787", "Boeing", "China Eastern Airlines", "MU787-09"),
        new Plane(467, "747-8", "Boeing", "China Airlines", "CI747-10"),
        new Plane(180, "A320", "Airbus", "AirAsia", "AK320-11"),
        new Plane(72, "ATR 72", "Firefly", "Firefly", "FY72-12"),
    };
}
