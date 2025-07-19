package com.kafka.kafkatest.adapter.input.controller.response;

import com.kafka.kafkatest.adapter.input.controller.request.ItemRequest;
import lombok.Builder;

import java.util.List;

@Builder
public record ClientResponse(
  String clientId,
  List<ItemRequest> items
) {
}
