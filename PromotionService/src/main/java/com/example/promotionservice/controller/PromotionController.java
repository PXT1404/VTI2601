package com.example.promotionservice.controller;

import com.example.promotionservice.common.BaseResponse;
import com.example.promotionservice.dto.CreatePromotionDTO;
import com.example.promotionservice.entity.Promotion;
import com.example.promotionservice.service.PromotionService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotions")
public class PromotionController {
    private final PromotionService promotionService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Promotion>> create(@RequestBody CreatePromotionDTO createPromotionDTO) {
        Promotion response = promotionService.create(createPromotionDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse<>(response, "Created success"));
    }
    @GetMapping("/{code}")
    public ResponseEntity<BaseResponse<Promotion>> findByCode(@PathVariable String code) {
        Promotion response = promotionService.findByCode(code);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse<>(response, "Found success"));
    }
}
