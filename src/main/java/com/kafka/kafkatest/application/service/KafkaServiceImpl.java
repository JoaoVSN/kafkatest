package com.kafka.kafkatest.application.service;

import com.kafka.kafkatest.adapter.input.controller.request.ClientRequest;
import com.kafka.kafkatest.adapter.input.controller.response.OrderResponse;
import com.kafka.kafkatest.application.KafkaService;
import com.kafka.kafkatest.application.domain.Order;
import com.kafka.kafkatest.application.mapper.TestMapper;
import com.kafka.kafkatest.application.port.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {
    @Autowired
    private MessageAdapter messageAdapter;

    @Override
    public List<OrderResponse> consumeKafkaMsg() {
        List<Order> orders = messageAdapter.consumeMessage();
        return TestMapper.INSTANCE.ordersListToOrderResponseList(orders);
    }

    @Override
    public OrderResponse producerKafkaMsg(ClientRequest body) {
        try{
            Order newOrder =  TestMapper.INSTANCE.clientResquestToOrder(UUID.randomUUID().toString(),body);
            Thread.sleep(2000);
            return TestMapper.INSTANCE.orderToOrderResponse(
                messageAdapter.produceMessage(newOrder)
            );
        }catch (InterruptedException exception){
            log.error("Deu problema no contador!!!", exception);
            throw new RuntimeException();
        }
    }
}
