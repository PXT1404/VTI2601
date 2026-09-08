package com.example.orderservice.controller;

import com.example.orderservice.common.BaseResponse;
import com.example.orderservice.dto.request.CreateOrderItem;
import com.example.orderservice.dto.response.OrderResponse;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.form.FormForOrdering;
import com.example.orderservice.service.OrderItemService;
import com.example.orderservice.service.OrderService;
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
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<BaseResponse<OrderResponse>> create(@Valid @RequestBody FormForOrdering order) {
        OrderResponse response = orderService.create(order);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse<>(response, "Created successfully"));
    }
}
