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
public class EWallet extends Payment{
    //variable
    private String mobileNo;
    private String walletType;
    
    public EWallet(){}
    
    public EWallet(Date paymentDate,double amount,boolean paidStatus, String mobileNo,String walletType){
        super(paymentDate,amount,paidStatus);
        this.mobileNo = mobileNo;
        this.walletType = walletType;
    }

    public EWallet(double amount,String mobileNo,String walletType){
        super(amount);
        this.mobileNo = mobileNo;
        this.walletType = walletType;
    }
    
    //if there is any non-number value in the mobile number it will return false
    public boolean verifyMobile(){
        try{
            Integer.parseInt(this.mobileNo);
        }
        catch(NumberFormatException error){
            return false;
        }
        return true;
    }
    
    //getter
    public String getMobileNo(){
        return mobileNo;
    }
    
    public String getWalletType(){
        return walletType;
    }
    
    //setter
    public void setMobileNo(String mobileNo){
        this.mobileNo = mobileNo;
    }
    
    public void setWalletType(String walletType){
        this.walletType = walletType;
    }
    
    public String toString(){
        return super.toString()+String.format("Mobile No : %s\nWallet Type : %s", mobileNo,walletType);
    }
    
    public boolean equals(Object o){
        EWallet wallet = (EWallet)o; //downcasting
        if(wallet.mobileNo.equals(mobileNo)) //equals()-->String values
            return true;
        else
            return false;
    }
    
    // if mobileNo is valid then pay success
    public boolean makePayment(){
        if(verifyMobile())
            return true;
        else
            return false;
    }
}
