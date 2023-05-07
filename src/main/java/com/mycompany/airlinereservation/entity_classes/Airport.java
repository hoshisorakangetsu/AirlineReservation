/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

import com.mycompany.airlinereservation.util.Choicer;

/**
 *
 * @author user
 */
public class Airport implements Choicer {
    private String location;
    private String state;
    private String country;
    private String name;
    private int numberOfGates;
    
    public Airport(){}
    
    public Airport(String location, String state, String country, String name, int numberOfGates){
        this.location = location;
        this.state = state;
        this.country = country;
        this.name = name;
        this.numberOfGates = numberOfGates;
    }
    
    public String getLocation(){
        return location;
    }
    
    public String getState(){
        return state;
    }
    
    public String getCountry(){
        return country;
    }
    
    public String getName(){
        return name;
    }
    
    public int getNumberOfGates(){
        return numberOfGates;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setNumberOfGates(int numberOfGates){
        this.numberOfGates = numberOfGates;
    }
    
    public String toString(){
        return String.format("Airport location : %s\nAirport State : %s\n"
                + "Airport Country : %s\nAirport Name : %s\nNumber of gates : %d", location, state, country,name,numberOfGates);
    }
    
    public boolean equals(Object o){
        if (!(o instanceof Airport))
            return false;
        Airport airport = (Airport)o; //downcasting
        if(airport.name.equals(name)) //equals()-->String values
            return true;
        else
            return false;
    }
    
    public boolean isSameCountry(Airport airport){
        Airport countries = (Airport)airport; //downcasting
        if(countries.country.equals(country)) //equals()-->String values
            return true;
        else
            return false;
    }

    public String toChoiceString() {
        return name + "(" + country + ")";
    }
}
