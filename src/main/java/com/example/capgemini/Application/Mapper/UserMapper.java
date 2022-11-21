package com.example.capgemini.Application.Mapper;

import com.example.capgemini.Application.dto.UserDTO;
import com.example.capgemini.Application.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        return new ModelMapper().map(user, UserDTO.class);
    }
}
