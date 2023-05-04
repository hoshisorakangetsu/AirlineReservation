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
import java.util.regex.*;
public class Card extends Payment {
    private String cardNo;
    public Card(){
        super();
    }
    public Card (Date paymentDate,double amount,String cardNo){
        super (paymentDate,amount);
        this.cardNo=cardNo;
    }
    public String getCardNo(){
        return cardNo;
    }
    
    public boolean verifyCard(String creditCardNumber){
        String regex = "^(?:(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})|(6(?:011|5[0-9]{2})[0-9]{12})|(3[47][0-9]{13})|(3(?:0[0-5]|[68][0-9])[0-9]{11})|((2131|1800|35\\d{3})\\d{11}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(creditCardNumber);
         if (matcher.matches()) {
            return true;
        } else {
            return false;
            // Prompt an error message or take appropriate action
        }

    }
    public boolean equals(Object o){
       if (o instanceof Card){
           return (super.equals(o));
       }else {
           return false;
       }
   }
    
    public String toString (){
        return super.toString()+String.format("cardNo: %s", cardNo);
    }
}
