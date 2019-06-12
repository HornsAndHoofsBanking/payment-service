package com.andersenlab.payment.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andersenlab.payment.domain.Payment;

@Repository
public interface PaymentDao extends CrudRepository<Payment, Long> {

}
