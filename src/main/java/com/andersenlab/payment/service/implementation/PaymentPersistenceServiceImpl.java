package com.andersenlab.payment.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersenlab.payment.dao.elasticsearch.PaymentDaoElasticSearch;
import com.andersenlab.payment.dao.jpa.PaymentDaoJpa;
import com.andersenlab.payment.domain.Payment;
import com.andersenlab.payment.service.PaymentPersistenceService;

@Service
public class PaymentPersistenceServiceImpl implements PaymentPersistenceService {

    @Autowired
    private PaymentDaoElasticSearch paymentDaoElasticSearch;

    @Autowired
    private PaymentDaoJpa paymentDaoJpa;

    @Override
    public void save(Payment payment) {
        saveWithJpa(payment);
        saveWithElasticSearch(payment);
    }

    private void saveWithJpa(Payment payment) {
        paymentDaoJpa.save(payment);
    }

    private void saveWithElasticSearch(Payment payment) {
        paymentDaoElasticSearch.save(payment);
    }

}
