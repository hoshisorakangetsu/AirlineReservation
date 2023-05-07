package com.mycompany.airlinereservation.driver;

import com.mycompany.airlinereservation.entity_classes.Airport;
import com.mycompany.airlinereservation.util.ArrayUtils;
import com.mycompany.airlinereservation.util.ConsoleInput;
import com.mycompany.airlinereservation.util.PrettyPrint;

public class AirportDriver {
    private static Airport[] airports = new Airport[] {
        new Airport("Selangor", "Selangor", "Malaysia", "KUL", 16),
        new Airport("Penang", "Penang", "Malaysia", "PEN", 6),
        new Airport("Johor", "Johor", "Malaysia", "JHB", 4),
        new Airport("Sabah", "Sabah", "Malaysia", "BKI", 8),
        new Airport("Sarawak", "Sarawak", "Malaysia", "KCH", 6),
        new Airport("Shandong", "Shandong", "China", "TNA", 20),
        new Airport("Guangdong", "Guangdong", "China", "SZX", 20),
        new Airport("Hainan", "Hainan", "China", "HAK", 10),
        new Airport("Sichuan", "Sichuan", "China", "CTU", 16),
        new Airport("Singapore", "Singapore", "Singapore", "SIN", 15),
        new Airport("New York", "New York", "United States", "JFK", 20),
        new Airport("London", "London", "United Kingdom", "LHR", 20),
        new Airport("Tokyo", "Tokyo", "Japan", "NRT", 14),
        new Airport("Sydney", "New South Wales", "Australia", "SYD", 18),
        new Airport("Dubai", "Dubai", "United Arab Emirates", "DXB", 20),
    };

    public static Airport[] getAirports() {
        return airports;
    }

    public static Airport getAirport(int index) throws IndexOutOfBoundsException {
        if (index >= airports.length || index < 0)
            throw new IndexOutOfBoundsException(index);
        return airports[index];
    }

    public static void addAirport() {
        String location = ConsoleInput.getString("Enter the location of the new airport: ", false);
        String state = ConsoleInput.getString("Enter the state of the new airport: ", false);
        String country = ConsoleInput.getString("Enter the country of the new airport: ", false);
        String name;
        // name is unique
        nameRepeat:
        while (true) {
            name = ConsoleInput.getString("Enter the name of the new airport (IATA abbr)", false);
            for (Airport a : airports) {
                if (a.getName().equals(name)) {
                    System.out.println("The name has repeated, please choose another one");
                    continue nameRepeat;
                }
            }
            break;
        }
        int numberOfGates = ConsoleInput.getInt("Enter number of gates: ");
        ConsoleInput.clearBuffer();

        // create the new airport
        Airport newAirport = new Airport(location, state, country, name, numberOfGates);
        airports = ArrayUtils.appendIntoArray(airports, newAirport);
    }

    public static void viewAirport() {
        int choice = ConsoleInput.getChoice(airports, "Enter number of airport to view in detail: ");
        ConsoleInput.clearBuffer();
        PrettyPrint.printDetailsCard(airports[choice - 1]);
        // block until user decides everything is ok
        ConsoleInput.getString("Press [enter] to continue");
    }

    public static void editAirport() {
        int choice = ConsoleInput.getChoice(airports, null);
        ConsoleInput.clearBuffer();
        Airport airportToEdit = airports[choice - 1];
        String location = ConsoleInput.getString("Enter the location (leave empty to not change): ");
        String state = ConsoleInput.getString("Enter the state (leave empty to not change): ");
        String country = ConsoleInput.getString("Enter the country (leave empty to not change): ");
        String name;
        // name is unique
        nameRepeat:
        while (true) {
            name = ConsoleInput.getString("Enter the name (IATA abbr, leave empty to not change)");
            if (name.isBlank())
                break;
            for (Airport a : airports) {
                if (a.getName().equals(name)) {
                    System.out.println("The name has repeated, please choose another one");
                    continue nameRepeat;
                }
            }
            break;
        }
        int numberOfGates = ConsoleInput.getInt(
            String.format(
                "Enter number of gates (Current: %d): ", 
                airportToEdit.getNumberOfGates()
            )
        );
        ConsoleInput.clearBuffer();

        if (!location.isBlank())
            airportToEdit.setLocation(location);
        if (!state.isBlank())
            airportToEdit.setState(state);
        if (!country.isBlank())
            airportToEdit.setCountry(country);
        if (!name.isBlank())
            airportToEdit.setName(name);
        airportToEdit.setNumberOfGates(numberOfGates);
        
        System.out.println("Airport edited successfully!");
    }

}
