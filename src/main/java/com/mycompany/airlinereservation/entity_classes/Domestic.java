/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

/**
 *
 * @author Neoh Soon Chee
 */
import java.util.regex.*;
public class Domestic extends PlaneTicket{
    //variable
    private String passengerIC;
    
    //method
    public Domestic(){};
    
    public Domestic(PlaneSchedule sched, String seatType, String passengerName, int passengerAge, double price, String passengerIC, String passengerPassport){
        super(sched, seatType, passengerName, passengerAge, price, passengerPassport);
        this.passengerIC = passengerIC;
    }
    
    //setter
    public void setPassengerIC(String passengerIC){
        this.passengerIC = passengerIC;
    }
    
    //getter
    public String getPassengerIC(){
        return passengerIC;
    }
    
    public String toString(){
        return String.format(super.toString()+"Passenger IC: %s\n", passengerIC);
    }
    
    public boolean equals(Object o){
        Domestic sched = (Domestic)o;
        if(sched.passengerIC.equals(passengerIC))
            return true;
        else 
            return false;
    }
    
    //method to verify is the document complete
    public boolean verifyDocuments(){
        if(passengerIC != null || super.getPassengerPassport() != null){ //if IC or Passport is available then true
            if(passengerIC != null){ //if IC is available then check
                if(passengerIC.length() == 12){ //is the length 12 then check
                    for(int i = 0; i < passengerIC.length(); i++){ //check is all int
                        if(passengerIC.charAt(i)<'0' || passengerIC.charAt(i)>'9'){ //if is < 0 or > 9, not int, charAt returns char, can just compare between chars for easy handling
                            System.out.println("IC is invalid");
                            return false; //return false as fail
                        }
                    }
                    // all is between 0 - 9
                    return true;
                }else{ //length is not 12
                    System.out.println("IC is invalid");
                }
            }else{ //check passport
                if (super.getPassengerPassport() == null || super.getPassengerPassport().isBlank()){
                    System.out.println("Passport is required");
                    return false;
                }
                //create pattern from regex
                Pattern singaporeRegex = Pattern.compile("[A-Za-z][0-9]{7}[A-Za-z]");
                Pattern chinaRegex = Pattern.compile("[A-Z][0-9]{8,9}");
                Pattern uKRegex = Pattern.compile("\\d{9}");
                Pattern malaysiaRegex = Pattern.compile("[A-Z][0-9]{8}");
                
                //match regex with passport
                String passengerPassport = super.getPassengerPassport();
                Matcher singapore = singaporeRegex.matcher(passengerPassport);
                Matcher china = chinaRegex.matcher(passengerPassport);
                Matcher uK = uKRegex.matcher(passengerPassport);
                Matcher malaysia = malaysiaRegex.matcher(passengerPassport);
                
                //identify match or not
                if(singapore.find())
                    return true;
                else if (china.find())
                    return true;
                else if (uK.find())
                    return true;
                else if (malaysia.find())
                    return true;
                else{
                    System.out.println("Passport is invalid");
                    return false;
                }
            }
        }
        
        //if all the above checks failed
        System.out.println("IC and passport is invalid");
        return false; //return false as fail
    }
}
