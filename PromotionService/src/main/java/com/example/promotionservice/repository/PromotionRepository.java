package com.example.promotionservice.repository;

import com.example.promotionservice.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, String> {
   Promotion save(Promotion promotion);

    Promotion findByCode(String code);
}
