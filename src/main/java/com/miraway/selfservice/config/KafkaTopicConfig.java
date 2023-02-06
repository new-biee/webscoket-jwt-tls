package com.miraway.selfservice.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${kafka.deviceTopic}")
    private String deviceTopic;

    @Value("${kafka.settingTopic}")
    private String settingTopic;

    @Value("${kafka.uaaTopic}")
    private String uaaTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic deviceTopic() {
        return TopicBuilder.name(deviceTopic).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic settingTopic() {
        return TopicBuilder.name(settingTopic).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic uaaTopic() {
        return TopicBuilder.name(uaaTopic).partitions(3).replicas(1).config(TopicConfig.RETENTION_MS_CONFIG, "259200").build();
    }
}
