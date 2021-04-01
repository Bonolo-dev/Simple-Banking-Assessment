/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 *
 * @author omphilebonolomonale
 */
@Entity
@Table(	name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private String idNumber;
    private Float currentBalance;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_account_types",
            joinColumns = @JoinColumn(name = "user_id"),	
            inverseJoinColumns = @JoinColumn(name = "account_type_id"))
    private Set<AccountType> accountType=new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_transactions",
            joinColumns = @JoinColumn(name = "user_id"),	
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private Set<Transaction> transaction=new HashSet<>();
    
    public User(){
        
    }

    public User(String name, String lastName, String idNumber, Float currentBalance) {
        this.name = name;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.currentBalance = currentBalance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Set<AccountType> getAccountType() {
        return accountType;
    }

    public void setAccountType(Set<AccountType> accountType) {
        this.accountType = accountType;
    }

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }
    
}
