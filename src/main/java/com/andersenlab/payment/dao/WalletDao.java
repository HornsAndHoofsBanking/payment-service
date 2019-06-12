package com.andersenlab.payment.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andersenlab.payment.domain.Wallet;

@Repository
public interface WalletDao extends CrudRepository<Wallet, Long> {

}
