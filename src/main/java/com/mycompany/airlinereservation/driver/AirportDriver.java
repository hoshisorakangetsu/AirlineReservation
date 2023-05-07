package com.mycompany.airlinereservation.driver;

import com.mycompany.airlinereservation.entity_classes.Airport;

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
}
