package com.example.capgemini.Application.Mapper;

import com.example.capgemini.Application.dto.UserDetailsDTO;
import com.example.capgemini.Application.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * The dto mapper. The class that does the conversion from User object into UserDTO object
 */
@Service
public class UserMapper {

    /**
     * The conversion of the User object into UserDTO
     * @param userDetailsDTO UserDetailsDTO class
     * @return User class
     */
    public static User toUserDetailsEntity(UserDetailsDTO userDetailsDTO) {
        return new ModelMapper().map(userDetailsDTO, User.class);
    }
}
