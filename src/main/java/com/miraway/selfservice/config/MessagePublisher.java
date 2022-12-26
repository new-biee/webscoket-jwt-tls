package com.miraway.selfservice.config;

import com.miraway.selfservice.domain.Message;

public interface MessagePublisher {
    void publish(Message message);
}
