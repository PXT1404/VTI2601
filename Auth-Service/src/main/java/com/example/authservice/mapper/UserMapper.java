package com.example.authservice.mapper;

import com.example.authservice.entity.User;
import com.example.authservice.request.UserRegistrationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUser (UserRegistrationDTO userRegistrationDTO);
}
