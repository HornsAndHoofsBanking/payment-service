package com.andersenlab.payment.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andersenlab.payment.domain.Account;

@Repository
public interface AccountDao extends CrudRepository<Account, Long> {

}
