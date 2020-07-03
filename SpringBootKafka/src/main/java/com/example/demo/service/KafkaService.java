package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaService
{
    @KafkaListener(topics = "customer-region-1")
    public void listen(String message) {
        System.out.println("Received Messasge in group foo: " + message);
    }
}
