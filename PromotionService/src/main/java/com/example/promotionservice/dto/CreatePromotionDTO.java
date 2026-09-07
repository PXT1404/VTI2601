package com.example.promotionservice.dto;

import com.example.promotionservice.entity.DiscountType;
import jakarta.persistence.Column;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePromotionDTO {
    private String code;

    private DiscountType discountType;

    private Float discountValue;

    private Float minOrderValue;

    private Integer usageLimit;

    private Instant startDate;

    private Instant endDate;
}
