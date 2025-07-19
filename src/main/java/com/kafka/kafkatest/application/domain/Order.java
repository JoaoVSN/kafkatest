package com.kafka.kafkatest.application.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private String orderId;
  private ClientOrder clientOrder;
}
