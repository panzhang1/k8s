package com.zp.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KafkaConsumeExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumeExample.class);

    public static void main(String[] args) {
        KafkaConsumeExample test = new KafkaConsumeExample();
        test.consume();
    }

    public void consume() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "group.zp");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("sasl.mechanism", "PLAIN");
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.jaas.config","org.apache.kafka.common.security.plain.PlainLoginModule required username=\"sebadmin\" password=\"local123!\";");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        List<PartitionInfo> partitions = consumer.partitionsFor("kafka.topic.zp.test3.v1");
        for (PartitionInfo partition : partitions) {
            LOGGER.info("-------------------partition = " + partition.partition() + ", topic = " + partition.topic());
        }

        consumer.subscribe(Arrays.asList("kafka.topic.zp.test1.v1","kafka.topic.zp.test2.v1"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                LOGGER.info("-------------------topic = " + record.topic() + ", offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
            }
        }
    }
}
