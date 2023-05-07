/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

/**
 *
 * @author Neoh Soon Chee
 */
public class International extends PlaneTicket{
    //variable
    private String passengerVisa;
    
    //method
    public International(){}
    
    public International(PlaneSchedule sched, String seatType, String passengerName, int passengerAge, double price, String passengerPassport, String passengerVisa){
        super(sched, seatType, passengerName, passengerAge, price, passengerPassport);
        this.passengerVisa = passengerVisa;
    }
    
    //setter
    public void setPassengerVisa(String passengerVisa){
        this.passengerVisa = passengerVisa;
    }
    
    //getter    
    public String getPassengerVisa(){
        return passengerVisa;
    }
    
    public String toString(){
        return String.format(super.toString() + "Passenger Visa: %s\n", passengerVisa);
    }
    
    public boolean equals(Object o){
        if(o instanceof International)
            return super.equals(o);
        else
            return false;
    }
    
    //method to verify documents is complete
    public boolean verifyDocuments(){
        if(super.getSched().getVisaRequired()){ //if visa is required
            if((super.getPassengerPassport() == null || super.getPassengerPassport().isBlank()) && (passengerVisa == null || passengerVisa.isBlank())){ //if passport and visa is null
                System.out.println("The visa and passport is required."); //document not complete, return false as fail
                return false;
            }else{ //passport and visa is complete
                return true; //return true as success
            }
        }else{ //if visa is not required
            if(super.getPassengerPassport() == null || super.getPassengerPassport().isBlank()){ //if passport is null
                System.out.println("The passport is required."); //document not complete, return false as fail
                return false;
            }else{ //passport is complete
                return true; //return true as success
            }
        }
    }
}
