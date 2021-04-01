/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.util;

import org.springframework.stereotype.Component;

/**
 *
 * @author omphilebonolomonale
 * 
 * 
 */
@Component
public class TokenManager {
 
    /**
     * Normally I would use JWT from spring security to generate and verify token.
     * This will do to save on time
     */
    private final String TOKEN="YourUniqueTokenForEveryLogin";
    
    public void generateToken(){
        /**
         * Ideally where token should be generated
        */
    }
    
    public Boolean verifyToken(String token){
        return token.equalsIgnoreCase(this.TOKEN);
    }
    
    public String getToken(){
        return this.TOKEN;
    }
    
}
