package com.kafka.kafkatest.adapter.output;

import com.kafka.kafkatest.application.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class KafkaImplTest {

  @InjectMocks
  KafkaImpl kafka;

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @Mock
  private Properties kafkaConsumerProperties;

  @Mock
  private Order messsage;

  @BeforeEach
  void init(){
    ReflectionTestUtils.setField(kafka, "topic", "kafka-test");
  }

  @Test
  void consumeMessage() {
    assertDoesNotThrow(
        () -> kafka.consumeMessage(),
        "Should noty throw"
    );
  }

  @Test
  void produceMessage() {
    assertDoesNotThrow(
        () -> kafka.produceMessage(messsage),
        "Should noty throw"
    );
  }
}