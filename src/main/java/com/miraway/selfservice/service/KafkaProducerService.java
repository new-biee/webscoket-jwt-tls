package com.miraway.selfservice.service;

import com.miraway.selfservice.domain.Message;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    public final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, SimpMessagingTemplate simpMessagingTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessage(Message message) {
        UUID uuid = UUID.randomUUID();
        kafkaTemplate.send(message.getTopic(), uuid.toString(), message.toString());
        logger.info(">>> Send message :{}", message);
        simpMessagingTemplate.convertAndSend(message.getTopic(), message.getMessage());
        logger.info(">>> Send message websocket :{}", message);
    }
}
