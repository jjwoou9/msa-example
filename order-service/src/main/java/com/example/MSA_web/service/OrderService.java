package com.example.MSA_web.service;

import com.example.MSA_web.dto.OrderDto;
import com.example.MSA_web.jpa.OrderEntity;

public interface OrderService {
	OrderDto createOrder(OrderDto orderDto);
	OrderDto getOrderByOrderId(String orderId);
	Iterable<OrderEntity> getOrdersByUserId(String userId);
}
