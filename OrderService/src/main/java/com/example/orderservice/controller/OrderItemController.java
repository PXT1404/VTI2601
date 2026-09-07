package com.example.orderservice.controller;

import com.example.orderservice.common.BaseResponse;
import com.example.orderservice.dto.request.CreateOrderItem;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orderitems")
public class OrderItemController {
    private final OrderItemService orderItemService;


    @PostMapping
    public ResponseEntity<BaseResponse<OrderItem>> create(@Valid @RequestBody CreateOrderItem createOrderItem) {
        OrderItem response = orderItemService.create(createOrderItem);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse<>(response, "Created successfully"));
    }
}