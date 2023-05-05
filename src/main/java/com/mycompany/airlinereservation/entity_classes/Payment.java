/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

/**
 *
 * @author user
 */
import java.util.Date;
public class Payment {
    private Date paymentDate;
    private double amount;
    
    public Payment(){}
    
    public Payment(Date paymentDate, double amount){
        this.paymentDate = paymentDate;
        this.amount = amount;
    }
    
    public Date getPaymentDate(){
        return paymentDate;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public void setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }
    
    public String toString(){
        return String.format("Payment Date : %s\nAmount : RM%.2f", paymentDate, amount);
    }
    
    public boolean equals(Object o){
        Payment paid = (Payment)o; //downcasting
        if(paid.paymentDate.equals(paymentDate)) //equals()-->String values
            return true;
        else
            return false;
    }
}
