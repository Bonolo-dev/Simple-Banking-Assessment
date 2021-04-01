/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.wonderlabz.bank.wonderlabz.entity.AccountType;
import za.co.wonderlabz.bank.wonderlabz.entity.User;

/**
 *
 * @author omphilebonolomonale
 */
@Repository
public interface AccountTypeDao extends CrudRepository<AccountType, Integer>{
    
}
