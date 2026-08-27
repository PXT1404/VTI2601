package com.example.departmentservice.service;

import com.example.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDepartmentService {
    public List<Department> getAllDepartments();

    public Department getDepartmentById(Short id);
}
