/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author omphilebonolomonale
 */
public class RegisterDto {
    //More fields specific to the user can be added here e.g address, cell number email and etc.

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String idNumber;
    
    private float currentBalance;
    @NotBlank
    private String accountType;
    
    @NotBlank
    private String password;
    
    public RegisterDto(){
        
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
