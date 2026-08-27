package com.example.positionservice.service;


import com.example.positionservice.entity.Position;
import com.example.positionservice.repository.IPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositonService implements IPositionService{
    @Autowired
    private IPositionRepository positionRepository;

    @Override
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public Position getPositionById(Short id) {
        return positionRepository.getById(id);
    }

}
