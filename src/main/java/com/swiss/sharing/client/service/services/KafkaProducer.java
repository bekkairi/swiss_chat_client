package com.swiss.sharing.client.service.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiss.sharing.client.service.exceptions.UserServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);


    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    public <T> void sendMessage(T t, String topic) {
        Function<T, String> mapObject = (el) -> {

            try {
                return mapper.writeValueAsString(t);
            } catch (JsonProcessingException e) {
                throw new UserServiceException(e);
            }
        };
        String jsonObject = mapObject.apply(t);
        LOGGER.info(String.format("Message sent -> %s", jsonObject));
        kafkaTemplate.send(topic, jsonObject);
    }
}
