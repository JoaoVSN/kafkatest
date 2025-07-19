package com.kafka.kafkatest.adapter.input.controller.response;


import lombok.Builder;

@Builder
public record ItemResponse(
    Integer itemId,
    String description
){
}
