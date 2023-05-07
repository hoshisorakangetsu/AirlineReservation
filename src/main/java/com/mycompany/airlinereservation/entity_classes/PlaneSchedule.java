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
    
    public PlaneSchedule(){}
    
    public PlaneSchedule(double baggageAllowance, Date flightDate, Airport src, Airport dest, Plane plane, boolean visaRequired){
        this.baggageAllowance = baggageAllowance;
        this.flightDateTime = flightDate;
        this.src = src;
        this.dest = dest;
        this.plane = plane;
        this.visaRequired = visaRequired;
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
    
    public String toString(){
        return String.format(
            "Baggage Allowance : %.2fkg\nFlight Date Time : %s\nFrom : \n%s\nTo : \n%s\nPlane : \n%s\nVisa Required : \n%s\n", 
            baggageAllowance,
            new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(flightDateTime),
            src.toString(), dest.toString(), plane.toString(), visaRequired ? "yes" : "no");
    }
    
    public boolean equals(Object o){
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
            this.src + " - " + this.dest + " " +
            this.plane.getId();
    }
}
