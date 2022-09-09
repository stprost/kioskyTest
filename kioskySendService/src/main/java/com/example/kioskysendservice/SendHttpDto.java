package com.example.kioskysendservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class SendHttpDto {

    public static final String URL = "http://localhost:8081/api/entity/save";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    private final OkHttpClient httpClient = new OkHttpClient();

    public void sendNewDto(SourceDto dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json"), objectMapper.writeValueAsString(dto));
            Request request = new Request.Builder()
                    .url(URL)
                    .addHeader("Authorization", createHeader(USERNAME, PASSWORD))
                    .post(body)
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                if (response.code() == 200) {
                    log.info("Получен ответ со статусом 200(OK)");
                }
            } catch (IOException e) {
                log.error("Ошибка при получении ответа от сервиса: " + e.getMessage());
            }
        } catch (JsonProcessingException e) {
            log.error("Ошибка конвертиции объекта в json: " + e.getMessage());
        }
    }

    private String createHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + new String(encodedAuth);
    }
}
