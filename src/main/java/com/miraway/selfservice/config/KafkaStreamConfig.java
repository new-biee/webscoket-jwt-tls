package com.miraway.selfservice.config;

import com.miraway.selfservice.common.Constants;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

@Configuration
@EnableKafka
public class KafkaStreamConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServer;

    @Value("${spring.kafka.streams.application-ids}")
    private String applicationId;
    //    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    //    public KafkaStreamsConfiguration kStream() {
    //        Map<String, Object> properties = new HashMap<>();
    //        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
    //        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
    //        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    //        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    //
    //        return new KafkaStreamsConfiguration(properties);
    //    }
    //
    //
    //    @Bean
    //    public KStream<String, String> kStream(StreamsBuilder kStreamBuilder) {
    //        KStream<String, String> stream = kStreamBuilder.stream(Constants.KAFKA_INPUT_TOPIC);
    //        stream
    //                .mapValues((ValueMapper<String, String>) String::toUpperCase)
    //                .groupByKey()
    //                .windowedBy(TimeWindows.of(Duration.ofMillis(1000)))
    //                .reduce((String value1, String value2) -> value1 + value2, Named.as("windowStore"))
    //                .toStream()
    //                .map((windowedId, value) -> new KeyValue<>(windowedId.key(), value))
    //                .filter((i, s) -> s.length() > 40)
    //                .to(Constants.KAFKA_OUTPUT_TOPIC);
    //        stream.print(Printed.toSysOut());
    //        return stream;
    //    }
}
