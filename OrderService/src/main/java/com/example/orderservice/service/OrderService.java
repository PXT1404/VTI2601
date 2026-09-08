package com.example.orderservice.service;

import com.example.orderservice.dto.response.OrderResponse;
import com.example.orderservice.form.FormForOrdering;
import org.hibernate.query.Order;

public interface OrderService {
    OrderResponse create (FormForOrdering order);
}
