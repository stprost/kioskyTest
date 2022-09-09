package com.example.kioskygetservice;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class KioskyGetServiceApplication {

    public static final String QUEUE_NAME = "testQueue";
    public static List<SourceDto> dtoList = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(KioskyGetServiceApplication.class, args);
    }

    @Bean
    public Queue myQueue() {
        return new Queue(QUEUE_NAME, false);
    }
}
