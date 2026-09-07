package com.example.promotionservice.service.impl;

import com.example.promotionservice.dto.CreatePromotionDTO;
import com.example.promotionservice.entity.Promotion;
import com.example.promotionservice.mapper.PromotionMapper;
import com.example.promotionservice.repository.PromotionRepository;
import com.example.promotionservice.service.PromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionMapper promotionMapper;
    private final PromotionRepository promotionRepository;
    @Override
    public Promotion create(CreatePromotionDTO createPromotionDTO) {
        Promotion promotion = promotionMapper.to (createPromotionDTO);

        return promotionRepository.save(promotion);
    }

}
