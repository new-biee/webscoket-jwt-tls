package com.miraway.selfservice.service;

import com.miraway.selfservice.common.Constants;
import com.miraway.selfservice.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    public final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message) {
        logger.info(">>> Send message :{}", message);
        kafkaTemplate.send(Constants.KAFKA_INPUT_TOPIC, message);
    }
}
