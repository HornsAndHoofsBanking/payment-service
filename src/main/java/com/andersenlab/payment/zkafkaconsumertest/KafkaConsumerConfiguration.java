package com.andersenlab.payment.zkafkaconsumertest;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.andersenlab.payment.domain.Payment;

@Configuration
public class KafkaConsumerConfiguration {

    public ConsumerFactory<String, Payment> consumerFactory(String bootstrapServers, String groupId) {
        Map<String, Object> configs = new HashMap<>();

        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(),
                new JsonDeserializer<>(Payment.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Payment> kafkaListenerContainerFactory(
            @Value("${payment.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${payment.kafka.group-id}") String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, Payment> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(bootstrapServers, groupId));
        return factory;
    }

}
