package com.miraway.selfservice.config;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageSubscriber implements MessageListener {

    public static List<Object> messages = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        messages.add(message.getChannel());
        messages.add(message.toString());
    }
}
