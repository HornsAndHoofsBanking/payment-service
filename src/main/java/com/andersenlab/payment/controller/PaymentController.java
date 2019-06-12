package com.andersenlab.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andersenlab.payment.domain.Payment;
import com.andersenlab.payment.service.PaymentService;
import com.andersenlab.payment.service.StatisticsService;

@RestController
@RequestMapping("/api/rest/payment")
public class PaymentController {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    PaymentService paymentService;

    @PostMapping("/{accountId}")
    public void transfer(@PathVariable Long accountId, @RequestBody Payment payment) {
        payment.setAccountId(accountId);
        Payment executed = paymentService.execute(payment);
        statisticsService.sendReport(executed);
    }

}
