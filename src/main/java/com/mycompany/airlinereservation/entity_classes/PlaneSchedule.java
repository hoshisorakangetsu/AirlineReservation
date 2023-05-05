/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

/**
 *
 * @author user
 */
import java.util.Date;
public class PlaneSchedule {
    private double baggageAllowance;
    private Date flightDate;
    private Airport src;
    private Airport dest;
    private Plane plane;
    private boolean visaRequired;
    
    public PlaneSchedule(){}
    
    public PlaneSchedule(double baggageAllowance, Date flightDate, Airport src, Airport dest, Plane plane, boolean visaRequired){
        this.baggageAllowance = baggageAllowance;
        this.flightDate = flightDate;
        this.src = src;
        this.dest = dest;
        this.plane = plane;
        this.visaRequired = visaRequired;
    }
    
    public double getBaggageAllowance(){
        return baggageAllowance;
    }
    
    public Date getFlightDate(){
        return flightDate;
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
    
    public void setFlightDate(Date flightDate){
        this.flightDate = flightDate;
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
        return String.format("Baggae Allowance : RM%.2f\nFlight Date : %s"
                ,baggageAllowance,flightDate);
    }
    
    public boolean equals(Object o){
        PlaneSchedule schedule = (PlaneSchedule)o; //downcasting
        if(schedule.flightDate.equals(flightDate)) //equals()-->String values
            return true;
        else
            return false;
    }
}
