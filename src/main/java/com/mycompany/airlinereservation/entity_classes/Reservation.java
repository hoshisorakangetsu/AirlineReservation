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
public class Reservation implements Choicer {
    private PlaneTicket[] tickets;
    private Payment pay;
    private Customer cust;
    public Reservation (){
        
    }
    public Reservation(PlaneTicket[] tickets, Payment pay,Customer cust){
        this.tickets=tickets;
        this.pay=pay;
        this.cust=cust;
    }
    public PlaneTicket[] getPlaneTicket(){
        return tickets;
    }
    public Payment getPay(){
        return pay;
        
    }
    public Customer getCust(){
        return cust;
        
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
    public String toString() {
        String res=String.format("Customer: \n%s\nPayment: \n%s", cust.toString(), pay.toString());
        for(int i=0;i<tickets.length;i++){
            res += String.format("\nTicket #%d:\n%s",i+1,tickets[i].toString());
        }
        return res;
    }
   public boolean equals(Object o){
       if (o instanceof Reservation ){
           return (cust.equals(((Reservation)o).cust));
       }else {
           return false;
       }
   }
    // can based on the schedule, which is on the tickets to make a selection
    public String toChoiceString(){
        return tickets[0].getSched().toChoiceString();
    }
}
