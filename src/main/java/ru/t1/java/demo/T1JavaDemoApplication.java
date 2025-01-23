package ru.t1.java.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.t1.java.demo.service.KafkaProducer;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class T1JavaDemoApplication implements CommandLineRunner {

    private final KafkaProducer kafkaProducer;

    public static void main(String[] args) {
        SpringApplication.run(T1JavaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaProducer.sendMessage("test", "Hello from Spring Boot!");
    }
}
