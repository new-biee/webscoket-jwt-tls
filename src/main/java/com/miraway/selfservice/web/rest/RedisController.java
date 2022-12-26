package com.miraway.selfservice.web.rest;

import com.miraway.selfservice.config.RedisMessagePublisher;
import com.miraway.selfservice.config.RedisMessageSubscriber;
import com.miraway.selfservice.domain.Message;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    public static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisMessagePublisher messagePublisher;

    @PostMapping("/publisher")
    public void publish(@RequestBody Message message) {
        logger.info(">> publishing : {}", message);
        messagePublisher.publish(message);
    }

    @GetMapping("/subscriber")
    public List<Object> getMessage() {
        logger.info(">> subscriber : {}", RedisMessageSubscriber.messages);
        return RedisMessageSubscriber.messages;
    }
}
