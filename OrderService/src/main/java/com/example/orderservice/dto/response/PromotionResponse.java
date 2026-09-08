package com.example.orderservice.dto.response;

import com.example.orderservice.dto.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionResponse {
    private String code;

    private DiscountType discountType;

    private Float discountValue;

    private Float minOrderValue;

    private Integer usageLimit;

    private Instant startDate;

    private Instant endDate;
}
