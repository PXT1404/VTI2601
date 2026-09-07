package com.example.orderservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItem {
    private String orderId;

    private String productId;

    private String promotionId;

    private Integer quantity;

    private Double price;

    private Double discount;

    private Double total;

}
