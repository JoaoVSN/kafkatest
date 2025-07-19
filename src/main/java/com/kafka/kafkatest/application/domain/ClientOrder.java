package com.kafka.kafkatest.application.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrder {
  String clientId;
  List<Items> items;
}
