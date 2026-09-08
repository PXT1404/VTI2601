package com.example.orderservice.mapper;

import com.example.orderservice.dto.response.OrderResponse;
import com.example.orderservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "total", target = "total")
    OrderResponse create (Order order);
}
