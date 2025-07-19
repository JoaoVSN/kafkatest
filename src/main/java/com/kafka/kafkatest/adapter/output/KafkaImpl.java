package com.kafka.kafkatest.adapter.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.kafkatest.application.domain.Order;
import com.kafka.kafkatest.application.port.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
@Slf4j
public class KafkaImpl implements MessageAdapter {

    @Value("${spring.kafka.consumer.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Properties kafkaConsumerProperties;

    private static List<Order> orderResponses = new ArrayList<>();

    @Override
    public List<Order> consumeMessage(){
        ObjectMapper mapper = new ObjectMapper();

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaConsumerProperties)) {
            consumer.subscribe(Collections.singletonList(topic));
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));

            for (ConsumerRecord<String, String> record : records) {
                Order newOrder = mapper.readValue(record.value(),Order.class);
                orderResponses.add(newOrder);
            }

            return orderResponses;
        }catch (RuntimeException | JsonProcessingException exception){
            log.error("Erro ao consumir mensagem",exception);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Order produceMessage(Order message) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            kafkaTemplate.send(topic, mapper.writeValueAsString(message));
            return message;
        }catch(RuntimeException | JsonProcessingException exception){
            log.error("Erro ao enviar mensagem",exception);
            throw new RuntimeException(exception);
        }
    }
}
