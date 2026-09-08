package com.example.orderservice.feignClient;

import com.example.orderservice.common.BaseResponse;
import com.example.orderservice.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ProductService", path = "/api/v1")
public interface ProductFeignClient {
    @GetMapping("/products/{id}")
    BaseResponse<ProductResponse> getProductById(@PathVariable("id") String id);
}