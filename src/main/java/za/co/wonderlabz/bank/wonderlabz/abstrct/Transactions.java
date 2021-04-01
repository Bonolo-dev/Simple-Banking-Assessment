/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.abstrct;


import java.util.Date;
import za.co.wonderlabz.bank.wonderlabz.entity.Transaction;
import za.co.wonderlabz.bank.wonderlabz.entity.TransactionType;
import za.co.wonderlabz.bank.wonderlabz.entity.User;

/**
 *
 * @author omphilebonolomonale
 */
public abstract class Transactions {
    
    protected float updatedBalance;
    protected float amount;
    protected Transaction updatedTransaction;
    protected User user;
    
    public abstract void withdraw ();
    
    public boolean transfer(float amount,String transAccount){
        return true;
    }
    
    public float deposit(){
        
        this.updatedBalance=user.getCurrentBalance() + this.amount;
        return this.updatedBalance;
    }
    

    public float getUpdatedBalace(){
    
        return this.updatedBalance;
    }

    
    public Transaction buildTransactionObj(TransactionType transType){
     
        this.updatedTransaction = new Transaction(new Date(System.currentTimeMillis())
                ,this.getUpdatedBalace(),transType);
        
        return this.updatedTransaction;
    }
    
    public User buildUserObj(){
        this.user.getTransaction().add(updatedTransaction);
        this.user.setCurrentBalance(updatedBalance);
        
        return user;
    }
    
}
