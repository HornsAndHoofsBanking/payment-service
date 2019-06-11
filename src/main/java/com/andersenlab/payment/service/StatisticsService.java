package com.andersenlab.payment.service;

import com.andersenlab.payment.domain.Payment;

public interface StatisticsService {

    void sendReport(Payment payment);

}