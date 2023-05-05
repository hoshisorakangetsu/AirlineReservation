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
    private String mobileNo;
    private String walletType;
    
    public EWallet(){}
    
    public EWallet(Date paymentDate,double amount, String mobileNo){
        super(paymentDate,amount);
        this.mobileNo = mobileNo;
    }
    
    public static boolean verifyMobile(String mobileNo){
        int phoneNum = 0;
        try{
            phoneNum = Integer.parseInt(mobileNo);
        }
        catch(NumberFormatException error){
            return false;
        }
        return true;
    }
    
    public String getMobileNo(){
        return mobileNo;
    }
    
    public String getWalletType(){
        return walletType;
    }
    
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
}
