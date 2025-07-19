package com.kafka.kafkatest.application.service;

import com.kafka.kafkatest.adapter.input.controller.request.ClientRequest;
import com.kafka.kafkatest.adapter.input.controller.response.OrderResponse;
import com.kafka.kafkatest.application.domain.Order;
import com.kafka.kafkatest.application.port.MessageAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaServiceImplTest {
  @InjectMocks
  private KafkaServiceImpl service;

  @Mock
  private MessageAdapter messageAdapter;
  @Mock
  private OrderResponse response;
  @Mock
  private ClientRequest body;
  @Mock
  private Order domain;

  @Test
  void testConsumeKafkaMsg(){
    when(messageAdapter.consumeMessage()).thenReturn(List.of(domain));

    assertDoesNotThrow(
        () -> service.consumeKafkaMsg(),
        "Should not throw"
    );

  }

  @Test
  void producerKafkaMsg(){
    assertDoesNotThrow(
        () -> service.producerKafkaMsg(body),
        "Should not throw"
    );

  }

}