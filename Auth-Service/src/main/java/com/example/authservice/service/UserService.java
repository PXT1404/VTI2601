package com.example.authservice.service;

import com.example.authservice.entity.User;
import com.example.authservice.request.LoginRequestDTO;
import com.example.authservice.request.UserRegistrationDTO;
import com.example.authservice.response.LoginResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(UserRegistrationDTO dto);
    LoginResponseDTO login (LoginRequestDTO dto);
}
