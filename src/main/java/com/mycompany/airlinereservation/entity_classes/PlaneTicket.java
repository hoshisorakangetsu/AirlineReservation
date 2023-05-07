/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

import com.mycompany.airlinereservation.util.Choicer;

/**
 *
 * @author Neoh Soon Chee
 */
public abstract class PlaneTicket implements Choicer {
    //variable
    private String id;
    private PlaneSchedule sched;
    private String seatType;
    private String passengerName;
    private String passengerPassport;
    private int passengerAge;
    private double price;
    private static int assignedId = 1001;
    
    //method
    
    public PlaneTicket() {}
    
    public PlaneTicket(PlaneSchedule sched, String seatType, String passengerName, int passengerAge, double price, String passengerPassport){
        this.sched = sched;
        this.seatType = seatType;
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.price = price;
        this.passengerPassport = passengerPassport;
        this.id = "T" + assignedId;
        assignedId++;
    }
    
    //setter
    public void setSched(PlaneSchedule sched){
        this.sched = sched;
    }
    
    public void setSeatType(String seatType){
        this.seatType = seatType;
    }
    
    public void setPassengerName(String passengerName){
        this.passengerName = passengerName;
    }
    
    public void setPassengerAge(int passengerAge){
        this.passengerAge = passengerAge;
    }
    
    public void setPrice(double price){
        this.price = price;
    }

    public void setPassengerPassport(String passengerPassport){
        this.passengerPassport = passengerPassport;
    }
    
    //getter
    public PlaneSchedule getSched(){
        return sched;
    }
    
    public String getSeatType(){
        return seatType;
    }
    
    public String getPassengerName(){
        return passengerName;
    }
    
    public int getPassengerAge(){
        return passengerAge;
    }
    
    public double getPrice(){
        return price;
    }

    public String getPassengerPassport(){
        return passengerPassport;
    }
    
    public String toString(){
        return String.format("Plane Schedule: \n%s\nPassenger Name: %s\nPassenger Age: %d\nSeat Type: %s\nPrice: %.2f\n",sched.toString(), passengerName, passengerAge,seatType, price);
    }
    
    public boolean equals(Object o){
        PlaneTicket planeTicket = (PlaneTicket)o;
        if(planeTicket.id.equals(id))
            return true;
        else
            return false;
    }

    public String toChoiceString() {
        // only id is unique enough to determine the which ticket
        return this.id + ": " + this.passengerName;
    }
    
    //method to upgrade the seatType to BUSINESS
    public boolean upgrade(){
        //check whether is ECONOMY seat type
        if(seatType.equalsIgnoreCase("ECONOMY")){
            this.seatType = "BUSINESS"; //change to BUSINESS
            this.price = price*1.4; //increase the price
            return true; //return as upgrade sucessful
        }else{
            System.out.println("The seat type of the ticket is already is BUSINESS class.");
            return false; //return as upgrade fail
        }
    }

    public abstract boolean verifyDocuments();
}
