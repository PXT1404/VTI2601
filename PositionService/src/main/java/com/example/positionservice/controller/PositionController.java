package com.example.positionservice.controller;


import com.example.positionservice.dto.PositionDto;
import com.example.positionservice.entity.Position;
import com.example.positionservice.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/positions")
@CrossOrigin("*")
public class PositionController {
    @Autowired
    private IPositionService positionService;

    @GetMapping
    public ResponseEntity<?> getAllPositions() {
        List<Position> entities = positionService.getAllPositions();
        List<PositionDto> dtos = new ArrayList<>();

        for (Position entity : entities) {
            PositionDto dto = new PositionDto(entity.getId(), entity.getName().toString());
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPositionByID(@PathVariable(name = "id") short id) {
        Position position = positionService.getPositionById(id);
        PositionDto dto = new PositionDto(position.getId(), position.getName().toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
