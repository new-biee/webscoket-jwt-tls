package com.miraway.selfservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    public final Logger logger = LoggerFactory.getLogger(WebsocketController.class);

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebsocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/send/message")
    public String broadcastGroupMessage(@Payload String message) {
        //        simpMessagingTemplate.convertAndSend("/topic/destination", message);
        //Sending this message to all the subscribers
        logger.info("[Message] websocket received -> {}", message);
        return message;
    }
}
