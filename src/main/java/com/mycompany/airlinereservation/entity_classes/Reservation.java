/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mycompany.airlinereservation.util.Choicer;

/**
 *
 * @author yong
 */
public class Reservation implements Choicer {
    private PlaneTicket[] tickets;
    private String id; 
    private Payment pay;
    private Customer cust;
    private Date dateCreated;
    private static int currentID = 1001;

    public Reservation (){
        
    }
    public Reservation(PlaneTicket[] tickets, Payment pay,Customer cust){
        this.tickets=tickets;
        this.pay=pay;
        this.cust=cust;
        this.dateCreated=new Date();
        this.id = "R" + currentID;
        currentID++;
    }
    public PlaneTicket[] getTickets(){
        return tickets;
    }
    public Payment getPay(){
        return pay;
        
    }
    public Customer getCust(){
        return cust;
        
    }
    public Date getDateCreated(){
        return dateCreated;
    }
    public void setTicket(PlaneTicket[] tickets){
        this.tickets=tickets;
    }
    public void setPay(Payment pay){
        this.pay=pay;
    }
    public void setCust(Customer cust){
        this.cust=cust;
    }
    public void setDateCreated(Date d){
        this.dateCreated=d;
    }
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
        String res=String.format("Date Reserved:%s\nCustomer: \n%s\nPayment: \n%s",sdf.format(dateCreated),cust.toString(),pay.toString());
        for(int i=0;i<tickets.length;i++){
            res += String.format("\nTicket #%d:\n%s",i+1,tickets[i].toString());
        }
        return res;
    }
   public boolean equals(Object o){
       if (o instanceof Reservation ){
           return (id.equals(((Reservation) o).id));
       }else {
           return false;
       }
   }
    // can based on the schedule, which is on the tickets to make a selection
    public String toChoiceString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
        return sdf.format(dateCreated) + " " + tickets[0].getSched().toChoiceString();
    }
}
