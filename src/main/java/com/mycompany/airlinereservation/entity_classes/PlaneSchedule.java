/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

import java.text.SimpleDateFormat;
/**
 *
 * @author user
 */
import java.util.Date;

import com.mycompany.airlinereservation.util.Choicer;
public class PlaneSchedule implements Choicer {
    private double baggageAllowance;
    private Date flightDateTime;
    private Airport src;
    private Airport dest;
    private Plane plane;
    private boolean visaRequired;
    private double basePrice;
    private int seatsAvailable; // auto determined
    
    public PlaneSchedule(){}
    
    public PlaneSchedule(double baggageAllowance, Date flightDateTime, Airport src, Airport dest, Plane plane, boolean visaRequired, double basePrice){
        this.baggageAllowance = baggageAllowance;
        this.flightDateTime = flightDateTime;
        this.src = src;
        this.dest = dest;
        this.plane = plane;
        this.visaRequired = visaRequired;
        this.basePrice = basePrice;
        this.seatsAvailable = this.plane.getPassengerCapacity();
    }
    
    public double getBaggageAllowance(){
        return baggageAllowance;
    }
    
    public Date getFlightDateTime(){
        return flightDateTime;
    }
         
    public Airport getSrc(){
        return src;
    }
    
    public Airport getDest(){
        return dest;
    }
    
    public Plane getPlane(){
        return plane;
    }
    
    public boolean getVisaRequired(){
        return visaRequired;
    }

    public double getBasePrice(){
        return basePrice;
    }

    public int getSeatsAvailable(){
        return seatsAvailable;
    }
    
    public void setBaggageAllowance(double baggageAllowance){
        this.baggageAllowance = baggageAllowance;
    }
    
    public void setFlightDateTime(Date flightDateTime){
        this.flightDateTime = flightDateTime;
    }
    
    public void setSrc(Airport src){
        this.src = src;
    }
    
    public void setDest(Airport dest){
        this.dest = dest;
    }
    
    public void setPlane(Plane plane){
        this.plane = plane;
    }
    
    public void setVisaRequired(boolean visaRequired){
        this.visaRequired = visaRequired;
    }

    public void setBasePrice(double basePrice){
        this.basePrice = basePrice;
    }

    public void setSeatAvailable(int seatAvailable){
        this.seatsAvailable = seatAvailable;
    }

    // reduce the number of available seats when tickets are booked
    public void bookTickets(int noOfTickets){
        this.seatsAvailable -= noOfTickets;
    }
    
    public String toString(){
        return String.format(
            "Baggage Allowance : %.2fkg\nFlight Date Time : %s\nFrom : \n%s\nTo : \n%s\nPlane : \n%s\nVisa Required : %s\nBase Price : RM%.2f\n", 
            baggageAllowance,
            new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(flightDateTime),
            src.toString(), dest.toString(), plane.toString(), visaRequired ? "yes" : "no", basePrice
        );
    }
    
    public boolean equals(Object o){
        if (!(o instanceof PlaneSchedule)) {
            return false;
        }
        PlaneSchedule schedule = (PlaneSchedule)o; //downcasting
        if(schedule.flightDateTime.equals(flightDateTime)) //equals()-->String values
            return true;
        else
            return false;
    }

    public String toChoiceString(){
        // return every single details that are needed to the user for them to make a choice and differentiate between schedules
        return 
            new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(flightDateTime) + " " +
            this.src.toChoiceString() + " -> " + this.dest.toChoiceString() + " " +
            this.plane.toChoiceString() + " " +
            String.format("RM%.2f %d seats available", this.basePrice, this.seatsAvailable);
    }
}
