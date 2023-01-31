//package com.miraway.selfservice.config;
//
//import com.miraway.selfservice.common.Constants;
//import java.util.Arrays;
//import org.apache.kafka.common.serialization.Serde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WordCountProcessor {
//    @Autowired
//    public void process(StreamsBuilder builder) {
//        final Serde<Integer> integerSerde = Serdes.Integer();
//        final Serde<String> stringSerde = Serdes.String();
//        final Serde<Long> longSerde = Serdes.Long();
//
//        KStream<Integer, String> textLines = builder.stream(Constants.KAFKA_INPUT_TOPIC, Consumed.with(integerSerde, stringSerde));
//
//        KTable<String, Long> wordCounts = textLines
//                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
//                .groupBy((key, value) -> value, Grouped.with(stringSerde, stringSerde))
//                .count();
//
//        wordCounts.toStream().to(Constants.KAFKA_OUTPUT_TOPIC, Produced.with(stringSerde, longSerde));
//    }
//}
