package com.kafka.kafkatest.adapter.input.controller.request;

import lombok.Builder;

import java.util.List;

@Builder
public record ClientRequest(
  String clientId,
  List<ItemRequest> items
) {
}
