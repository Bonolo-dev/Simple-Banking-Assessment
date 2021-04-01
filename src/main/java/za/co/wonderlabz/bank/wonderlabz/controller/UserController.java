/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.wonderlabz.bank.wonderlabz.dao.AccountTypeDao;
import za.co.wonderlabz.bank.wonderlabz.dao.UserDao;
import za.co.wonderlabz.bank.wonderlabz.dto.LoginDto;
import za.co.wonderlabz.bank.wonderlabz.entity.AccountType;
import za.co.wonderlabz.bank.wonderlabz.entity.User;
import za.co.wonderlabz.bank.wonderlabz.dto.RegisterDto;
import za.co.wonderlabz.bank.wonderlabz.service.UserService;
import za.co.wonderlabz.bank.wonderlabz.util.TokenManager;


/**
 *
 * @author omphilebonolomonale
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    AccountTypeDao accountTypeDao;
    
    @Autowired
    TokenManager tokenMan;
    
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody LoginDto login) throws JsonProcessingException {

        
        /**
         * Normally authentication token generation and verification 
         * is handled in spring security.But in this case I will skip 
         * all of that to save time
         * 
         * 
        */
        
        Optional<User> userObj = userDao.findByName(login.getName());
        if(!userObj.isPresent()){
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        User user = userObj.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(UserService.BuildUserDto(user,tokenMan));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody RegisterDto newUser) {
        
        
        //The balance should be obtained from else where to verify if user did pay the minimum
        //amount when required.
        User user= new User(newUser.getName(),newUser.getLastName()
                ,newUser.getIdNumber(),newUser.getCurrentBalance());
        
        
        //It would be better for Account type to have an enum instead of string
        AccountType accountTypeEntry = new AccountType(newUser.getAccountType());
        
        Set<AccountType> setAccType = new HashSet<AccountType>();
        setAccType.add(accountTypeEntry);
        accountTypeDao.save(accountTypeEntry);
        
        user.setAccountType(setAccType);
        userDao.save(user);

        return ResponseEntity.ok().body(HttpStatus.CREATED);
        
    }
}
