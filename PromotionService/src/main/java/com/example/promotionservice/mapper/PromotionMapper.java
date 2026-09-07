package com.example.promotionservice.mapper;

import com.example.promotionservice.dto.CreatePromotionDTO;
import com.example.promotionservice.entity.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    Promotion to (CreatePromotionDTO createPromotionDTO);
}
