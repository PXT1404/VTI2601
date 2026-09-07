package com.example.authservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull(message = "Tên đăng nhập không được để trống")
    @Size(min = 4, max = 20, message = "Tên đăng nhập phải từ 4-20 ký tự")
    private String username;

    @Email(message = "Email không hợp lệ")
    @NotNull(message = "Email không được để trống")
    private String email;

    @NotNull(message = "Tên không được để trống")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Họ không được để trống")
    @Column(name = "last_name")
    private String lastName;

}
