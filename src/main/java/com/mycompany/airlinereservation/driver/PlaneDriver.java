package com.mycompany.airlinereservation.driver;

import com.mycompany.airlinereservation.entity_classes.Plane;
import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ConsoleInput;
import com.mycompany.airlinereservation.util.PrettyPrint;

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

    public static Plane[] getPlanes() {
        return planes;
    }

    public static Plane getPlane(int index) throws IndexOutOfBoundsException {
        if (index >= planes.length || index < 0)
            throw new IndexOutOfBoundsException(index);
        return planes[index];
    }

    public static void addPlane() {
        int passengerCapacity = ConsoleInput.getInt("Enter passenger capacity: ");
        ConsoleInput.clearBuffer();
        String model = ConsoleInput.getString("Enter plane model: ", false);
        String manufacturer = ConsoleInput.getString("Enter manufacturer: ", false);
        String company = ConsoleInput.getString("Enter airline company: ", false);
        String id;
        // loop until id entered do not repeat
        idRepeat:
        while (true) {
            id = ConsoleInput.getString("Enter airplane ID: ", false);
            
            for (Plane p : planes) {
                if (p.getId().equals(id)) {
                    System.out.println("Plane ID has repeated, please enter another one");
                    continue idRepeat;
                }
            }
            break;
        }
        Plane newPlane = new Plane(passengerCapacity, model, manufacturer, company, id);
        planes = ArrayUtils.appendIntoArray(planes, newPlane);
    }

    public static void viewPlane() {
        int choice = ConsoleInput.getChoice(planes, "Enter number of the plane to view in detail: ");
        ConsoleInput.clearBuffer();
        PrettyPrint.printDetailsCard(planes[choice - 1]);
        // block until user decides everything is ok
        ConsoleInput.getString("Press [enter] to continue");
    }

    public static void editPlane() {
        // prompt all planes and get selection
        int choice = ConsoleInput.getChoice(planes, "Enter number of the plane to edit: ");
        Plane planeToEdit = planes[choice - 1];  // thank java for the reference magik
        int passengerCapacity = ConsoleInput.getInt(
            String.format(
                "Enter passenger capacity (Current: %d): ",
                planeToEdit.getPassengerCapacity()
            )
        );
        ConsoleInput.clearBuffer();
        String model = ConsoleInput.getString("Enter a model (leave blank to not change): ");
        String manufacturer = ConsoleInput.getString("Enter a manufacturer (leave blank to not change): ");
        String company = ConsoleInput.getString("Enter a company (leave blank to not change): ");
        String id;
        // loop until id entered do not repeat
        idRepeat:
        while (true) {
            id = ConsoleInput.getString("Enter airplane ID (leave blank to not change): ");

            if (id.isBlank())
                break;
            
            for (Plane p : planes) {
                if (p.getId().equals(id)) {
                    System.out.println("Plane ID has repeated, please enter another one");
                    continue idRepeat;
                }
            }
            break;
        }

        // do the set operations
        planeToEdit.setPassengerCapacity(passengerCapacity);
        if (!model.isBlank())
            planeToEdit.setModel(model);
        if (!manufacturer.isBlank())
            planeToEdit.setManufacturer(manufacturer);
        if (!company.isBlank())
            planeToEdit.setCompany(company);
        if (!id.isBlank())
            planeToEdit.setId(id);
        System.out.println("Plane edited successfully!");
    }

}
