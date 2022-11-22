package com.example.capgemini.Application.service;

import com.example.capgemini.Application.dao.UserRepository;
import com.example.capgemini.Application.dto.UserDTO;
import com.example.capgemini.Application.entity.User;
import com.example.capgemini.Application.exception.UserNotFoundException;
import com.example.capgemini.Application.service.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * The Service layer of User
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserBySurName(String surName) throws UserNotFoundException{
        User user = null;
        try {
            userRepository.findUserBySurName(surName);
            UserDTO userDTO = new UserDTO();

            userDTO.setName(user.getName());
            userDTO.setSurname(user.getSurname());
            userDTO.setBalance(user.getBalance());
            userDTO.setTransactions(user.getAccount().getTransactions());
            return userDTO;
        } catch (Exception e) {
            throw new UserNotFoundException("User not found by the surname :" + surName);
        }

    }
}
