package com.example.kioskygetservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqListener {

    @RabbitListener(queues = KioskyGetServiceApplication.QUEUE_NAME)
    public void listen(String dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            KioskyGetServiceApplication.dtoList.add(objectMapper.readValue(dto, SourceDto.class));
        } catch (JsonProcessingException e) {
            log.error("Ошибка при конвертации объекта из json: " + e.getMessage());
        }
        log.info("Получено сообщение из очереди: " + dto);
    }
}
