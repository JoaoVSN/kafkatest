package com.kafka.kafkatest.adapter.input.controller.request;


import lombok.Builder;

@Builder
public record ItemRequest(
    Integer itemId,
    String description
){
}
