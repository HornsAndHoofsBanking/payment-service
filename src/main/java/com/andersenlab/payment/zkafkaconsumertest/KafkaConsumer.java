package com.andersenlab.payment.zkafkaconsumertest;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.andersenlab.payment.domain.Payment;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "payment", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Payment payment) {
        System.out.println("CONSUMED: " + payment);
    }

}
