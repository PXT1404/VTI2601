package com.example.orderservice.feignClient;

import com.example.orderservice.common.BaseResponse;
import com.example.orderservice.dto.response.ProductResponse;
import com.example.orderservice.dto.response.PromotionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PromotionService", path = "/api/v1")
public interface PromotionFeignClient {
    @GetMapping("/promotions/{code}")
    BaseResponse<PromotionResponse> getPromotionByCode(@PathVariable("code") String code);
}