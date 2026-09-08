package com.example.orderservice.dto.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String id;

    private String userId;

    private String status;

    private Double total;
}
