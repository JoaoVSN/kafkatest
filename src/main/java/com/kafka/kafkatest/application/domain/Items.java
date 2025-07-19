package com.kafka.kafkatest.application.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Items {
  private Integer itemId;
  private String description;
}
