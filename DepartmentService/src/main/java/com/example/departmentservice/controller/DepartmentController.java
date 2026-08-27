package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        List<Department> entities = departmentService.getAllDepartments();
        List<DepartmentDto> dtos = new ArrayList<>();

        for (Department entity : entities) {
            DepartmentDto dto = new DepartmentDto(entity.getId(), entity.getName());
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDepartmentByID(@PathVariable(name = "id") short id) {
        Department department = departmentService.getDepartmentById(id);
        DepartmentDto dto = new DepartmentDto(department.getId(), department.getName());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
