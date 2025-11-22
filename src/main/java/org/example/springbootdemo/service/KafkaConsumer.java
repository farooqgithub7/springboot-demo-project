package org.example.springbootdemo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


public class KafkaConsumer {

    @KafkaListener(topics = "student-topic", groupId = "student-group")
    public void consume(String message) {
        System.out.println("Received Kafka message: " + message);
    }
}