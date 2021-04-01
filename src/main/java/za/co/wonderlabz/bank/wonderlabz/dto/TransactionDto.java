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
public class TransactionDto {
    @NotBlank
    private int userId;
    
    @NotBlank
    private float amount;

    public TransactionDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
