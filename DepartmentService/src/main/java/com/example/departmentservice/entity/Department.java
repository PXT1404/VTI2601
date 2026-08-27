package com.example.departmentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(catalog = "TestingSystem")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Department implements Serializable {
    @Id
    @Column(name = "DepartmentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "DepartmentName", length = 50)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;

}
