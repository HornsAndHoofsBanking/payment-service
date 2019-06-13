package com.andersenlab.payment.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.andersenlab.payment.domain.Payment;
import com.andersenlab.payment.service.StatisticsService;

@Component
public class StatisticsServiceImpl implements StatisticsService {

    private KafkaTemplate<String, Payment> paymentKafkaTemplate;
    private final String paymentStatisticsTopicName;

    @Autowired
    public StatisticsServiceImpl(
                                 @Qualifier("paymentKafkaTemplate") KafkaTemplate<String, Payment> paymentKafkaTemplate,
                                 @Value("${payment.kafka.payment-statistics.topic-name}") String paymentStatisticsTopicName) {

        this.paymentKafkaTemplate = paymentKafkaTemplate;
        this.paymentStatisticsTopicName = paymentStatisticsTopicName;
    }

    @Override
    public void sendReport(Payment payment) {
        paymentKafkaTemplate.send(paymentStatisticsTopicName, payment);
    }
}
