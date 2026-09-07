package com.example.orderservice.service;

import com.example.orderservice.dto.request.CreateOrderItem;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.service.impl.OrderItemServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface OrderItemService  {
    OrderItem create (CreateOrderItem  createOrderItem);

}
