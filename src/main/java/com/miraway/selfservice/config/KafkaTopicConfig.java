package com.miraway.selfservice.config;

import com.miraway.selfservice.common.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic inputTopic() {
        return TopicBuilder.name(Constants.KAFKA_INPUT_TOPIC).partitions(1).replicas(1).compact().build();
    }

    @Bean
    public NewTopic outputTopic() {
        return TopicBuilder.name(Constants.KAFKA_OUTPUT_TOPIC).partitions(1).replicas(1).compact().build();
    }
}
