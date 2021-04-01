/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.wonderlabz.bank.wonderlabz.dto.UserDto;
import za.co.wonderlabz.bank.wonderlabz.entity.AccountType;
import za.co.wonderlabz.bank.wonderlabz.entity.User;
import za.co.wonderlabz.bank.wonderlabz.util.TokenManager;

/**
 *
 * @author omphilebonolomonale
 */
@Service
public class UserService {

    public UserService() {
    }
    
    public static String BuildUserDto(User user, TokenManager tokenMan) throws JsonProcessingException{
        
        ObjectMapper mapper = new ObjectMapper();
        
        UserDto userDto = new UserDto(user.getId()
                ,user.getName(),user.getLastName(),user.getIdNumber()
                ,tokenMan.getToken(),user.getTransaction());
       
        return mapper.writeValueAsString(userDto);
    }
    
    public static boolean verifyAccountType(User user,String accountType){
        
        boolean correctAccount = false;
        for(AccountType accType: user.getAccountType()){
        
            if(accType.getAccountType().equals(accountType)){
                correctAccount=true;
            }
        }
        return correctAccount;
    }
}
