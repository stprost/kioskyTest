package com.example.kioskysendservice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class KioskySendServiceApplication {

    public static final String QUEUE_NAME = "testQueue";

    public static void main(String[] args) {
        SpringApplication.run(KioskySendServiceApplication.class, args);
    }

    @Bean
    public Queue myQueue() {
        return new Queue(QUEUE_NAME, false);
    }

}
