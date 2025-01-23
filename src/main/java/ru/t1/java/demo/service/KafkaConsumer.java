package ru.t1.java.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "test", groupId = "logging-service")
    public void listen(String message) {
        log.info(message);
        System.out.println("Received message: " + message);
    }

}
