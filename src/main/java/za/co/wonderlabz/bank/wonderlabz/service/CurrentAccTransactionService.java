/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.service;


import org.springframework.stereotype.Service;
import za.co.wonderlabz.bank.wonderlabz.abstrct.Transactions;
import za.co.wonderlabz.bank.wonderlabz.entity.User;
import za.co.wonderlabz.bank.wonderlabz.exception.InsufficientFundsException;

/**
 *
 * @author omphilebonolomonale
 */
@Service
public class CurrentAccTransactionService extends Transactions {
    
    protected final float OVERDRAFT = 100000;

    public CurrentAccTransactionService() {
    }

    public CurrentAccTransactionService(User user,float amount){
        this.user=user;
        this.amount=amount;
    }
    
    @Override
    public void withdraw() {
        
        float withdrawableBalance = user.getCurrentBalance() + OVERDRAFT;
        
        if(withdrawableBalance < this.amount){
            
            throw new RuntimeException(new InsufficientFundsException());
        }
        
        this.updatedBalance= user.getCurrentBalance()-this.amount;
     
    }

    
}
