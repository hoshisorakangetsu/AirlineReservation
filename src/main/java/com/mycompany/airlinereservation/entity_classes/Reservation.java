/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

/**
 *
 * @author yong
 */
public class Reservation {
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
    String result = "";
    for (PlaneTicket ticket : tickets) {
        result += String.format("Ticket: %s \n Payment : %.2f \n Customer: %s\n", ticket, pay, cust);
    }
    return result;
    }
   public boolean equals(Object o){
       if (o instanceof Reservation ){
           return (cust.equals(((Reservation)o).cust));
       }else {
           return false;
       }
   }
}
