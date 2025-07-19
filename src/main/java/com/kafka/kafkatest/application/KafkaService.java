package com.kafka.kafkatest.application;

import com.kafka.kafkatest.adapter.input.controller.request.ClientRequest;
import com.kafka.kafkatest.adapter.input.controller.response.OrderResponse;

import java.util.List;

public interface KafkaService {

    List<OrderResponse> consumeKafkaMsg();
    OrderResponse producerKafkaMsg(ClientRequest body);
}
