package com.example.promotionservice.service;

import com.example.promotionservice.dto.CreatePromotionDTO;
import com.example.promotionservice.entity.Promotion;

public interface PromotionService {
    Promotion create (CreatePromotionDTO createPromotionDTO);
    Promotion findByCode (String code);
}
