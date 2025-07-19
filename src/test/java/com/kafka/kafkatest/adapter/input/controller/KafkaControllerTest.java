package com.kafka.kafkatest.adapter.input.controller;

import com.kafka.kafkatest.adapter.input.controller.request.ClientRequest;
import com.kafka.kafkatest.adapter.input.controller.response.OrderResponse;
import com.kafka.kafkatest.application.KafkaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class KafkaControllerTest {
  @InjectMocks
  private KafkaController controller;

  @Mock
  private KafkaService service;

  @Mock
  private ClientRequest bodyRequest;

  @Mock
  private MockHttpServletRequest request;

  @Mock
  private HttpStatus status;

  @Mock
  private OrderResponse response;

  @Test
  void testCreatePayment() {

    assertDoesNotThrow(
        () -> controller.createPayment(bodyRequest, request),
        "Should not Throw");
    assertEquals(status, HttpStatus.CREATED);
  }

  @Test
  void testGetPaymentByPatient() {

    assertDoesNotThrow(
        () -> controller.getPaymentByPatient(request),
        "Should not Throw");
    assertEquals(status, HttpStatus.OK);
  }
}