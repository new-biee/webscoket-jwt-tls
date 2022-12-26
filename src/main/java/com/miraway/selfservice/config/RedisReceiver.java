package com.miraway.selfservice.config;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisReceiver.class);

    private static final List<Object> messages = new ArrayList<>();

    public void receiveNotificationMessage(String message) {
        LOGGER.info("Message Received from notification channel: <" + message + ">");
    }

    public void receiveCountMessage(String message) {
        LOGGER.info("Message Received from count channel: <" + message + ">");
    }

    public static List<Object> getNotificationMessage(String message) {
        messages.add(message);
        return messages;
    }
}
