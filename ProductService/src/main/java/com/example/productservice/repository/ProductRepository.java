package com.example.productservice.repository;

import com.example.productservice.dto.request.CreateProductDTO;
import com.example.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
    Product save(CreateProductDTO createProductDTO);
}

