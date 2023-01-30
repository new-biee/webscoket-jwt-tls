package com.miraway.selfservice.web.rest;

import com.miraway.selfservice.domain.Message;
import com.miraway.selfservice.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    KafkaProducerService producerService;

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        //Sending the message to kafka topic queue
        producerService.sendMessage(message);
    }
}
