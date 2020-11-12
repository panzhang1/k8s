package com.zp.example.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProduceExample {
    public static void main(String[] args) {
        KafkaProduceExample test = new KafkaProduceExample();
        test.produce();
    }
    
    public void produce() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("sasl.mechanism", "PLAIN");
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.jaas.config","org.apache.kafka.common.security.plain.PlainLoginModule required username=\"sebadmin\" password=\"local123!\";");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i=0; i< 10; i++) {
            producer.send(new ProducerRecord<>("kafka.topic.zp.test1.v1", "key1", "value" + i));
            producer.send(new ProducerRecord<>("kafka.topic.zp.test2.v1", "key1", "value" + i));
        }
        producer.close();
    }
}
