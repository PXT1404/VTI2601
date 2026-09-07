package com.example.productservice.service;

import com.example.productservice.dto.request.CreateProductDTO;
import com.example.productservice.entity.Product;

public interface ProductService {
    Product create (CreateProductDTO createProductDTO);
}
