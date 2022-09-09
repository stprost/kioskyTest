package com.example.kioskygetservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class GetController {

    @PostMapping("/entity/save")
    public ResponseEntity<Void> saveEntity(@RequestBody String dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            KioskyGetServiceApplication.dtoList.add(objectMapper.readValue(dto, SourceDto.class));
        } catch (JsonProcessingException e) {
            log.error("Ошибка при конвертации объекта из json: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
