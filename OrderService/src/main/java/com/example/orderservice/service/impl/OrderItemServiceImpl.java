package com.example.orderservice.service.impl;

import com.example.orderservice.dto.request.CreateOrderItem;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem create(CreateOrderItem createOrderItem) {
        double subTotal = createOrderItem.getQuantity() * createOrderItem.getPrice();
        double finalTotal = subTotal - createOrderItem.getDiscount();

        createOrderItem.setTotal(finalTotal);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(createOrderItem.getOrderId());
        orderItem.setProductId(createOrderItem.getProductId());
        orderItem.setPromotionId(createOrderItem.getPromotionId());
        orderItem.setQuantity(createOrderItem.getQuantity());

        // Gán trực tiếp vì Entity đang là Double
        orderItem.setPrice(createOrderItem.getPrice());
        orderItem.setDiscount(createOrderItem.getDiscount());
        orderItem.setFinalPrice(finalTotal);

        return orderItemRepository.save(orderItem);
    }
}
