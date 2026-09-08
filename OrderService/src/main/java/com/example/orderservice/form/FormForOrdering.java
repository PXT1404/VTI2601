package com.example.orderservice.form;

import com.example.orderservice.dto.request.CreateOrderItem;
import com.example.orderservice.dto.request.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormForOrdering {
    private List<OrderItemDTO> orderItems;
}
