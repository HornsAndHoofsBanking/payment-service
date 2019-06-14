package com.andersenlab.payment.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andersenlab.payment.domain.Payment;

@Repository
public interface PaymentDaoJpa extends CrudRepository<Payment, Long> {

}
