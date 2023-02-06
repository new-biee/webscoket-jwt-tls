package com.miraway.selfservice.web.rest;

import com.miraway.selfservice.domain.Message;
import com.miraway.selfservice.service.KafkaConsumerService;
import com.miraway.selfservice.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/send")
public class KafkaController {

    @Autowired
    private KafkaProducerService producerService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        //Sending the message to kafka topic queue
        producerService.sendMessage(message);
    }
}
