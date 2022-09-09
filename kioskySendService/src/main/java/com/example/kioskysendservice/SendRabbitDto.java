package com.example.kioskysendservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SendRabbitDto {

    private final RabbitTemplate rabbitTemplate;

    public void sendNewDto(SourceDto dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(KioskySendServiceApplication.QUEUE_NAME, objectMapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            log.error("Ошибка конвертиции объекта в json: " + e.getMessage());
        }
    }
}
