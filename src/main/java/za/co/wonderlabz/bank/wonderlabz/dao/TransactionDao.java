/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.wonderlabz.bank.wonderlabz.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.wonderlabz.bank.wonderlabz.entity.Transaction;

/**
 *
 * @author omphilebonolomonale
 */
@Repository
public interface TransactionDao extends CrudRepository<Transaction,Integer> {
    
}
