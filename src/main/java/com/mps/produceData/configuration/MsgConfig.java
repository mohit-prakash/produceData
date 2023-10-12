package com.mps.produceData.configuration;

import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class MsgConfig {

    @Bean
    public Map<String,Object> producerConfig(){
        Map<String,Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG,"pkc-xrnwx.asia-south2.gcp.confluent.cloud:9092");
//        props.put("spring.kafka.properties.bootstrap.servers","pkc-xrnwx.asia-south2.gcp.confluent.cloud:9092");
        props.put(KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, KafkaProtobufSerializer.class);
//        props.put("ssl.endpoint.identification.algorithm", "https");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + "HWTAAEDMBU6HX4LB" + "\" password=\"" + "vR30Yw2M51E7cF2uOX/411BeO1omnwJLDC5rY7wTcVN7wWhjfsJkZmnRFR2DWg6p" + "\";");
        props.put("security.protocol", "SASL_SSL");
        props.put("basic.auth.credentials.source","USER_INFO");
        props.put("basic.auth.user.info","3EPEMCRBDINCHBFI:070eesot6Bj9BP0L94jZeRaUw9jCOm+qUwP1JS1LYDG3VrmpedlY7jZ2zkhC6DK1");
        props.put("schema.registry.url","https://psrc-3w372.australia-southeast1.gcp.confluent.cloud");
//        props.put("session.timeout.ms",10000);
        return props;
    }
    @Bean
    public ProducerFactory<String,Object> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
