/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

import com.mycompany.airlinereservation.util.Choicer;

/**
 *
 * @author yong
 */



public class Plane implements Choicer {

    /**
     * @param args the command line arguments
     */
    private int passengerCapacity;
    private String model;
    private String manufacturer;
    private String company;
    private String id;
    public Plane(){}
    public Plane(int passengerCapacity, String model, String manufacturer, String company, String id){
        this.passengerCapacity=passengerCapacity;
        this.manufacturer=manufacturer;
        this.model=model;
        this.company=company;
        this.id=id;
    }
    public void setPassengerCapacity(int passengerCapacity){
        this.passengerCapacity=passengerCapacity;
    }
    public void setModel(String model){
        this.model=model;
    }
    public void setManufacturer(String manufacturer){
        this.manufacturer=manufacturer;
    }
    public void setCompany (String company ){
        this.company=company;
    }
    public void setId (String id ){
        this.id=id;
    }
    public int  getPassengerCapacity(){
        return passengerCapacity;
    }
    public String getModel(){
        return model;
    } 
    public String getManufacturer (){
        return manufacturer;
    }
    public String getCompany(){
        return company;
        
    }
    public String getId(){
        return id;
    }
    public String toString (){
        return String.format("Passenger Capacity: %d\nModel: %s \nManufacturer: %s \nCompany: %s\nPlane Id: %s\n", passengerCapacity, model,manufacturer, company,id);
    }

    public boolean equals(Object o){
       if (o instanceof Plane ){
           return (id.equals(((Plane)o).id));
       }else {
           return false;
       }
    }

    public String toChoiceString(){
        // id is unique enough to differentiate a plane
        return this.id;
    }
}
