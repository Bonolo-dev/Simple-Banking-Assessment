/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.wonderlabz.bank.wonderlabz.dao.AccountTypeDao;
import za.co.wonderlabz.bank.wonderlabz.dao.TransactionDao;
import za.co.wonderlabz.bank.wonderlabz.dao.TransactionTypeDao;
import za.co.wonderlabz.bank.wonderlabz.dao.UserDao;
import za.co.wonderlabz.bank.wonderlabz.dto.LoginDto;
import za.co.wonderlabz.bank.wonderlabz.dto.UserDto;
import za.co.wonderlabz.bank.wonderlabz.dto.TransactionDto;
import za.co.wonderlabz.bank.wonderlabz.entity.AccountType;
import za.co.wonderlabz.bank.wonderlabz.entity.TransactionType;
import za.co.wonderlabz.bank.wonderlabz.entity.User;
import za.co.wonderlabz.bank.wonderlabz.service.CurrentAccTransactionService;
import za.co.wonderlabz.bank.wonderlabz.service.SavingsAccTransactionService;
import za.co.wonderlabz.bank.wonderlabz.service.UserService;
import za.co.wonderlabz.bank.wonderlabz.util.TokenManager;


/**
 *
 * @author omphilebonolomonale
 */
@RestController
@RequestMapping("/transaction")
public class TransactionsController {
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    TransactionDao transactionDao;
    
    @Autowired
    TransactionTypeDao transactionTypeDao;
    
    @Autowired
    AccountTypeDao accTypeDao;
    
    @Autowired
    TokenManager tokenMan;
    
    /**
     * No token verification taking place here. As explained in the 
     * user controller spring security should handle all of this.
     * 
     * So we are assuming that every user is verified.
     * 
     */
    
    @PostMapping("/deposit")
    public ResponseEntity<?> Deposit(@RequestBody TransactionDto transaction) 
            throws JsonProcessingException{
        
        User user = this.verifyUser(transaction);
        
        if(user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();    
        }
        
        SavingsAccTransactionService depositObj = 
                new SavingsAccTransactionService(user,transaction.getAmount());
        
        depositObj.deposit();
        
        //transaction type should be an enum
        Optional<TransactionType> transTypeObj =transactionTypeDao.findByType("DEPOSIT");
        
        transactionDao.save(depositObj.buildTransactionObj(transTypeObj.get()));        
        
        userDao.save(depositObj.buildUserObj());
        
        return ResponseEntity.ok().body(UserService.BuildUserDto(user, tokenMan));
    }
        
    @PostMapping("/savings/withdraw")
    public ResponseEntity<?> savingsWithdraw
        (@RequestBody TransactionDto transaction)throws JsonProcessingException{
        
        User user = this.verifyUser(transaction);
        
        if(user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        if(!UserService.verifyAccountType(user,"SAVING")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"ErrorMessage\":\"Account error\"}");
        }
        
        SavingsAccTransactionService savingsObj =
                new SavingsAccTransactionService(user,transaction.getAmount());
        
        try{
            savingsObj.withdraw();
        }catch(RuntimeException ex){
            
            //Should report a better message for user
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("{\"ErrorMessage\": \""+ex.getMessage()+"\"}");
        }
        
        
        //Transaction type should be an enum
        Optional<TransactionType> transTypeObj =transactionTypeDao.findByType("WITHDRAW");
        
        transactionDao.save(savingsObj.buildTransactionObj(transTypeObj.get()));        
        
        userDao.save(savingsObj.buildUserObj());
        
        return ResponseEntity.ok()
                .body(UserService.BuildUserDto(user, tokenMan));
    }
    
    @PostMapping("/current/withdraw")
    public ResponseEntity<?> currentWithdraw(@RequestBody TransactionDto transaction) throws JsonProcessingException{
        
        User user = this.verifyUser(transaction);
        
        if(user==null){
            //Should have an error throwing class for this.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"ErrorMessage\":\"Try Again\"}");
        }
        
        //Should have an error throwing class for this
        if(!UserService.verifyAccountType(user,"CURRENT")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"ErrorMessage\":\"Account error\"}");
        }
        
        CurrentAccTransactionService currentObj =
                new CurrentAccTransactionService(user,transaction.getAmount());
        
        try{
            currentObj.withdraw();
        }catch(RuntimeException ex){
            
            //Should report a better message for user. Or better yet make a dto Issufficientfunds
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("{\"ErrorMessage\": \""+ex.getMessage()+"\"}");
        }
        
        //Transaction type should be an enum
        Optional<TransactionType> transTypeObj =transactionTypeDao.findByType("WITHDRAW");
        
        transactionDao.save(currentObj.buildTransactionObj(transTypeObj.get()));        
        
        userDao.save(currentObj.buildUserObj());
        
        return ResponseEntity.ok()
                .body(UserService.BuildUserDto(user, tokenMan));
    }
    
    @PostMapping("/savings/transfer")
    public ResponseEntity<?> savingstransfer(@Valid @RequestBody LoginDto login){
        
        /**
         * Savings Account Transfer will go through the whole savings withdraw process.
         * and then transfer the amount out.
         */
        return null;
    }
    
    @PostMapping("/current/transfer")
    public ResponseEntity<?> currenttransfer(@Valid @RequestBody LoginDto login){
        
        /**
         * Current Account Transfer will go through the whole savings withdraw process.
         * and then transfer the amount out.
         */
        return null;
    }
    
    
    private User verifyUser(TransactionDto transDto){
        
        Optional<User>userObj = userDao.findById(transDto.getUserId());
        
        return !userObj.isPresent() ? null : userObj.get();
    }
}
