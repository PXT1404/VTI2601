package com.example.orderservice.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionFormForOrdering {
    private String code;
    private Float price;
    private Instant time;
}
