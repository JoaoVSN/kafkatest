package com.kafka.kafkatest.adapter.input.controller;

import com.kafka.kafkatest.adapter.input.controller.request.ClientRequest;
import com.kafka.kafkatest.adapter.input.controller.response.OrderResponse;
import com.kafka.kafkatest.application.KafkaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = {"/pedidos"})
public class KafkaController {

    @Autowired
    private KafkaService service;

    @PostMapping
    public ResponseEntity<OrderResponse> createPayment(@RequestBody ClientRequest body, final HttpServletRequest request){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.producerKafkaMsg(body));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getPaymentByPatient(final HttpServletRequest request ){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(service.consumeKafkaMsg());
    }
}
