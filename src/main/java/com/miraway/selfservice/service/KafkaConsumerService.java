package com.miraway.selfservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    public final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final SimpMessagingTemplate simpMessagingTemplate;

    public KafkaConsumerService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @KafkaListener(
        topics = { "${kafka.deviceTopic}" },
        groupId = "${kafka.groupId}",
        containerFactory = "consumerKafkaListenerContainerFactoryOne"
    )
    public void consumeOne(String message) {
        logger.info("Message received -> {}", message);
        simpMessagingTemplate.convertAndSend("/topic/destination", message);
        logger.info("[Message] send received websocket -> {}", message);
    }

    @KafkaListener(
        topics = { "${kafka.settingTopic}", "${kafka.uaaTopic}" },
        groupId = "${kafka.groupId2}",
        containerFactory = "consumerKafkaListenerContainerFactoryTwo"
    )
    public void consumeTwo(String message) {
        logger.info("Message received -> {}", message);
        simpMessagingTemplate.convertAndSend("/topic/destination", message);
        logger.info("[Message] send received websocket -> {}", message);
    }
}
