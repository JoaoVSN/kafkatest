package com.kafka.kafkatest.adapter.input.controller.response;

public record OrderResponse(
    String orderId,
    ClientResponse clientOrder
) {
}
