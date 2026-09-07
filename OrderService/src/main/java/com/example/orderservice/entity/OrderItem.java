package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "order_id", nullable = true)
    private String orderId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "promotion_id")
    private String promotionId;

    private Integer quantity;

    private Double price;

    @Column(name = "discount_amount")
    private Double discount;

    @Column(name = "final_price")
    private Double total;
}
