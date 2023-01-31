package com.miraway.selfservice.config;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.miraway.selfservice.common.Constants;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//@Configuration
//@EnableKafka
//public class KafkaConsumerConfig {
//
//    @Bean
//    public ConsumerFactory<String, Object> consumerFactory() {
//        final Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVER);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.GROUP_ID);
//        config.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 1500);
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaConsumerFactory<>(config);
//    }
//
////    @Bean
////    public ConcurrentKafkaListenerContainerFactory<String, Object>
////    kafkaListenerContainerFactory() {
////        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
////                new ConcurrentKafkaListenerContainerFactory<>();
////        factory.setConsumerFactory(consumerFactory());
////        return factory;
////    }
//}
