package com.kafka.kafkatest.application.port;

import com.kafka.kafkatest.application.domain.Order;

import java.util.List;

public interface MessageAdapter {
    List<Order> consumeMessage();
    Order produceMessage(Order message);
}
