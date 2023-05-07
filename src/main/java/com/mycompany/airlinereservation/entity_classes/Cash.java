/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airlinereservation.entity_classes;

import java.util.Date;

/**
 *
 * @author yong
 */


public class Cash extends Payment{
    
    public Cash (){
        super();
    }
    public Cash (Date paymentDate,double amount,boolean paidStatus){
        super (paymentDate, amount, paidStatus);
    }
    public Cash (double amount){
        super (amount);
    }
    public String toString (){
        return super.toString();
    }
    public boolean equals(Object o){
       if (o instanceof Cash){
           return (super.equals(o));
       }else {
           return false;
       }
    }
    // cash payment always successes
    public boolean makePayment(){
        return true;
    }
}
