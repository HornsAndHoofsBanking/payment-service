package com.andersenlab.payment.controller;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andersenlab.payment.domain.Payment;
import com.andersenlab.payment.service.StatisticsService;

@RestController
@RequestMapping("/api/rest/payment")
public class PaymentController {

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping("/{amount}")
    public void transfer(@PathVariable int amount) {
        // right now it does nothing, just for testing sake
        Payment payment = new Payment();
        payment.setCurrency(Currency.getInstance("RUR"));
        payment.setAccountId(1L);
        payment.setAmount(BigDecimal.valueOf(amount));
        payment.setId(123L);
        statisticsService.sendReport(payment);
    }

}
