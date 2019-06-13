package com.andersenlab.payment.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andersenlab.payment.domain.Wallet;

@Repository
public interface WalletDao extends CrudRepository<Wallet, Long> {

}
