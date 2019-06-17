package com.andersenlab.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andersenlab.payment.domain.Payment;
import com.andersenlab.payment.service.PaymentPersistenceService;
import com.andersenlab.payment.service.PaymentService;
import com.andersenlab.payment.service.StatisticsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "payment" })
@RestController
@RequestMapping("/api/rest/payment")
public class PaymentController {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentPersistenceService paymentPersistenceService;

    @ApiOperation(value = "Execute payment from specified account",
                  notes = "Takes Payment object and executes it")
    @PostMapping
    public void transfer(@RequestBody Payment payment) {
        Payment executed = paymentService.execute(payment);
        paymentPersistenceService.save(executed);
        statisticsService.sendReport(executed);
    }

}
