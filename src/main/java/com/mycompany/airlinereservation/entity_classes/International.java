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
    private String passengerPassport;
    private String passengerVisa;
    
    //method
    public International(){}
    
    public International(PlaneSchedule sched, String seatType, String passengerName, int passengerAge, double price, String passengerPassport, String passengerVisa){
        super(sched, seatType, passengerName, passengerAge, price);
        this.passengerPassport = passengerPassport;
        this.passengerVisa = passengerVisa;
    }
    
    //setter
    public void setPassengerPassport(String passengerPassport){
        this.passengerPassport = passengerPassport;
    }
    
    public void setPassengerVisa(String passengerVisa){
        this.passengerVisa = passengerVisa;
    }
    
    //getter
    public String getPassengerPassport(){
        return passengerPassport;
    }
    
    public String getPassengerVisa(){
        return passengerVisa;
    }
    
    public String toString(){
        return String.format(super.toString() + "Passenger Passport: %s\nPassenger Visa: %s\n", passengerPassport, passengerVisa);
    }
    
    public boolean equals(Object o){
        International sched = (International)o;
        if(sched.passengerPassport.equals(passengerPassport))
            return true;
        else
            return false;
    }
    
    //method to verify documents is complete
    public boolean verifyDocuments(){
        if(super.getSched().getVisaRequired()){ //if visa is required
            if(passengerPassport == null && passengerVisa == null){ //if passport and visa is null
                System.out.println("The visa and passport is required."); //document not complete, return false as fail
                return false;
            }else{ //passport and visa is complete
                return true; //return true as success
            }
        }else{ //if visa is not required
            if(passengerPassport == null){ //if passport is null
                System.out.println("The passport is required."); //document not complete, return false as fail
                return false;
            }else{ //passport is complete
                return true; //return true as success
            }
        }
    }
}
