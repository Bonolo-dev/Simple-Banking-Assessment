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
public class LoginDto {
    @NotBlank
    private String name;
    
    @NotBlank
    private String password;

    public LoginDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
