package com.ushirobyte.food.auth_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ushirobyte.food.auth_service.model.ActivityLog;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TOPIC = "activity-log";

    public void send(ActivityLog log) {
        try {
            String message = objectMapper.writeValueAsString(log);
            kafkaTemplate.send(TOPIC, message);
        } catch (JsonProcessingException e) {
            // ignore for simplicity
        }
    }
}

