package com.example.positionservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(catalog = "TestingSystem")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "PositionID")
    private short id;

    @Column(name = "PositionName")
    @NotNull
    @Enumerated(EnumType.STRING)
    private PositionName name;

    public enum PositionName {
        Dev, Test, Scrum_Master, PM
    }
    @OneToMany(mappedBy = "position")
    List<Account> accounts;
}
