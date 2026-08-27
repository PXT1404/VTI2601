package com.example.positionservice.service;



import com.example.positionservice.entity.Position;

import java.util.List;

public interface IPositionService {
    public List<Position> getAllPositions();

    public Position getPositionById(Short id);
}
