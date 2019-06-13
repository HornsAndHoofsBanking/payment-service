package com.andersenlab.payment.service;

import com.andersenlab.payment.domain.Payment;

public interface PaymentPersistenceService {

    void save(Payment payment);
}
