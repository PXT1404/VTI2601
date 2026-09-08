package com.example.productservice.service.impl;

import com.example.productservice.dto.request.CreateProductDTO;
import com.example.productservice.dto.request.FindByIdDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(CreateProductDTO request) {
        Product product = new Product();

        product.setName(request.getName());
        if (request.getPrice() <= 0){
            throw new ApplicationContextException(" Giá phải lớn hơn 0!");
        }
        if (request.getStock() <= 0){
            throw new ApplicationContextException(" Số lượng sản phẩm phải lớn hơn 0!");
        }
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return productRepository.save(product);
    }

    @Override
    public Product findById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id: " + id));
        return product;
    }
}

