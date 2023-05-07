package com.mycompany.airlinereservation.driver;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mycompany.airlinereservation.entity_classes.PlaneSchedule;
import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ConsoleInput;
import com.mycompany.airlinereservation.util.PrettyPrint;

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

    public static PlaneSchedule[] getSchedules() {
        return schedules;
    }

    public static PlaneSchedule getSchedule(int index) throws IndexOutOfBoundsException {
        if (index >= schedules.length || index < 0)
            throw new IndexOutOfBoundsException(index);
        return schedules[index];
    }

    public static void addSchedule() {
        double baggageAllowance = ConsoleInput.getDouble("Enter baggage allowance: ");
        ConsoleInput.clearBuffer();
        Date flightDateTime = ConsoleInput.getDateTime("Enter the flight date time (dd/mm/yyyy HH:mm): ");
        int srcAirportIdx = ConsoleInput.getChoice(AirportDriver.getAirports(), "Enter number of the source airport: ");
        int destAirportIdx = ConsoleInput.getChoice(AirportDriver.getAirports(), "Enter number of the destination airport: ");
        int planeIdx = ConsoleInput.getChoice(PlaneDriver.getPlanes(), "Enter number of the plane: ");
        ConsoleInput.clearBuffer();
        boolean visaRequired = Character.toLowerCase(
            ConsoleInput.getChar("Is visa required? [Y/n]: ")
        ) == 'y';

        // create the schedule
        PlaneSchedule newSched = new PlaneSchedule(
            baggageAllowance, flightDateTime, 
            AirportDriver.getAirport(srcAirportIdx - 1), // in choice all is index + 1, -1 to get back ori index
            AirportDriver.getAirport(destAirportIdx - 1), 
            PlaneDriver.getPlane(planeIdx - 1), 
            visaRequired
        );

        schedules = ArrayUtils.appendIntoArray(schedules, newSched);
    }

    public static void viewSchedule() {
        int choice = ConsoleInput.getChoice(schedules, "Which schedule to look at: ");
        ConsoleInput.clearBuffer();
        PrettyPrint.printDetailsCard(schedules[choice - 1]);
        // block until user decides everything is ok
        ConsoleInput.getString("Press [enter] to continue");
    }

    public static void editSchedule() {
        int choice = ConsoleInput.getChoice(schedules, "Which schedule to look at: ");
        ConsoleInput.clearBuffer();
        PlaneSchedule schedToEdit = schedules[choice - 1];

        // cannot edit src and dest airport
        double baggageAllowance = ConsoleInput.getDouble(
            String.format(
                "Enter baggage allowance (Current: %.2f): ", 
                schedToEdit.getBaggageAllowance()
            )
        );
        ConsoleInput.clearBuffer();
        Date flightDateTime = ConsoleInput.getDateTime(
            String.format(
                "Enter the flight date time (dd/mm/yyyy HH:mm, Current: %s): ",
                new SimpleDateFormat("dd/MM/yyyy HH:mm")
            )
        );
        int planeIdx = ConsoleInput.getChoice(
            PlaneDriver.getPlanes(), 
            String.format(
                "Enter number of the plane (Current: %d): ", 
                ArrayUtils.indexOf(PlaneDriver.getPlanes(), schedToEdit.getPlane()) + 1 // in choice all is index + 1
            )
        );
        ConsoleInput.clearBuffer();

        schedToEdit.setBaggageAllowance(baggageAllowance);
        schedToEdit.setPlane(PlaneDriver.getPlane(planeIdx - 1));
        schedToEdit.setFlightDateTime(flightDateTime);
    }

}
