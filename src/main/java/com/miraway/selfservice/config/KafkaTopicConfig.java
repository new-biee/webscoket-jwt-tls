package com.miraway.selfservice.config;

import com.miraway.selfservice.common.Constants;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVER);
        return new KafkaAdmin(configs);
    }

    //    @Bean
    //    public NewTopic inputTopic() {
    ////        return TopicBuilder.name(Constants.KAFKA_INPUT_TOPIC).partitions(1).replicas(1).compact().build();
    //        return new NewTopic(Constants.KAFKA_INPUT_TOPIC, 1, (short) 1);
    //    }

    //    @Bean
    //    public NewTopic outputTopic() {
    //        return TopicBuilder.name(Constants.KAFKA_OUTPUT_TOPIC).partitions(1).replicas(1).compact().build();
    //    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic("baeldung", 1, (short) 1);
    }
}
