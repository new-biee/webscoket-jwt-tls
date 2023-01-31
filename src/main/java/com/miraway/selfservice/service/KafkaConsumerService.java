package com.miraway.selfservice.service;

import com.miraway.selfservice.common.Constants;
import com.miraway.selfservice.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    public final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    SimpMessagingTemplate template;

    @KafkaListener(topics = Constants.KAFKA_INPUT_TOPIC, groupId = Constants.GROUP_ID)
    public void consume(Message message) {
        logger.info("Message recieved -> {}", message);
        template.convertAndSend("/topic/group", message);
    }
}
