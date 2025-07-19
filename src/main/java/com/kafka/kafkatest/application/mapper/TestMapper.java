package com.kafka.kafkatest.application.mapper;

import com.kafka.kafkatest.adapter.input.controller.request.ClientRequest;
import com.kafka.kafkatest.adapter.input.controller.response.OrderResponse;
import com.kafka.kafkatest.application.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TestMapper {
  TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

  @Mapping(target = "orderId", source = "uuid")
  @Mapping(target = "clientOrder", source = "clientRequest")
  Order clientResquestToOrder(String uuid, ClientRequest clientRequest);

  OrderResponse orderToOrderResponse(Order order);

  List<OrderResponse> ordersListToOrderResponseList(List<Order> order);
}
