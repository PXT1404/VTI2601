package com.example.productservice.controller;

import com.example.productservice.common.BaseResponse;
import com.example.productservice.dto.request.CreateProductDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<BaseResponse<Product>> create(@Valid @RequestBody CreateProductDTO createProductDTO) {
        Product response = productService.create(createProductDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse<>(response, "Created success"));
    }
}