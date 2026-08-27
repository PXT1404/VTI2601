package com.example.positionservice.repository;


import com.example.positionservice.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionRepository extends JpaRepository<Position, Short> {
    public Position findByName(String name);

    public boolean existsByName(String name);
}
