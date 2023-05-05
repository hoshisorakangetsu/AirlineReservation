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
    private String passengerPassport;
    
    //method
    public Domestic(){};
    
    public Domestic(PlaneSchedule sched, String seatType, String passengerName, int passengerAge, double price, String passengerIC, String passengerPassport){
        super(sched, seatType, passengerName, passengerAge, price);
        this.passengerIC = passengerIC;
        this.passengerPassport = passengerPassport;
    }
    
    //setter
    public void setPassengerIC(String passengerIC){
        this.passengerIC = passengerIC;
    }
    
    public void setPassengerPassport(String passengerPassport){
        this.passengerPassport = passengerPassport;
    }
    
    //getter
    public String getPassengerIC(){
        return passengerIC;
    }
    
    public String getPassengerPassport(){
        return passengerPassport;
    }
    
    public String toString(){
        return String.format(super.toString()+"Passenger IC: %s\nPassenger Passport: %s\n", passengerIC, passengerPassport);
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
        if(passengerIC != null || passengerPassport != null){ //if IC or Passport is available then true
            if(passengerIC != null){ //if IC is available then check
                if(passengerIC.length() == 12){ //is the length 12 then check
                    for(int i = 0; i < passengerIC.length(); i++){ //check is all int
                        if(passengerIC.charAt(i)<0 || passengerIC.charAt(i)>9){ //if is < 0 or > 9, not int
                            System.out.println("Invalid IC input.");
                            return false; //return false as fail
                        }else{ //all condition correct
                            return true; //return true as success
                        }
                    }
                }else{ //length is not 12
                    System.out.println("The lenght of IC should be 12 character.");
                    return false; //return false as fail
                }
            }else{ //check passport
                //create pattern from regex
                Pattern SingaporeRegex = Pattern.compile("[A-Za-z][0-9]{7}[A-Za-z]");
                Pattern ChinaRegex = Pattern.compile("[A-Z][0-9]{8,9}");
                Pattern UKRegex = Pattern.compile("\\d{9}");
                Pattern MalaysiaRegex = Pattern.compile("[A-Z][0-9]{8}");
                
                //match regex with passport
                Matcher Singapore = SingaporeRegex.matcher(passengerPassport);
                Matcher China = ChinaRegex.matcher(passengerPassport);
                Matcher UK = UKRegex.matcher(passengerPassport);
                Matcher Malaysia = MalaysiaRegex.matcher(passengerPassport);
                
                //identify match or not
                if(Singapore.find())
                    return true;
                else if (China.find())
                    return true;
                else if (UK.find())
                    return true;
                else if (Malaysia.find())
                    return true;
                else
                    return false;
            }
        }else{ //if IC and Passport is not available then false
            System.out.println("The IC or Passport is required.");
            return false; //return false as fail
        }
    }
}
