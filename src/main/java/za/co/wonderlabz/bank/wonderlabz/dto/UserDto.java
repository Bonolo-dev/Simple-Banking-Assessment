/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.dto;

import java.util.Set;
import za.co.wonderlabz.bank.wonderlabz.entity.Transaction;

/**
 *
 * @author omphilebonolomonale
 */
public class UserDto {
    
    private int id;
    private String name;
    private String lastname;
    private String idNumber;
    private String token;
    private Set<Transaction> transactions;

    public UserDto(int id,String name, String lastname, String idNumber
            , String token,Set<Transaction> transactions) {
        
        this.id=id;
        this.name = name;
        this.lastname = lastname;
        this.idNumber = idNumber;
        this.token = token;
        this.transactions=transactions;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
    
    

    
}
