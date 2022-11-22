package com.example.capgemini.Application.service;


import com.example.capgemini.Application.dao.UserRepository;
import com.example.capgemini.Application.dto.UserDTO;
import com.example.capgemini.Application.entity.Account;
import com.example.capgemini.Application.entity.Transaction;
import com.example.capgemini.Application.entity.User;
import com.example.capgemini.Application.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setup() {
        this.userRepository = mock(UserRepository.class);
        this.userServiceImpl = new UserServiceImpl(this.userRepository);
    }

    @Test
    void getUserBySurName_shouldReturnUser_thenValidReturn() throws UserNotFoundException {
        User user = User.builder().customerID(1234).initialCredit(0).name("John").surname("Doe").balance(100).account(new Account(1234, 0,
                Arrays.asList(new Transaction(1234, 1234, 100,  LocalDateTime.now()),
                        new Transaction(1235, 1234, 10, LocalDateTime.now().plusDays(1))))).build();

        when(userRepository.findUserBySurName(user.getSurname())).thenReturn(user);
        UserDTO userDTO = userServiceImpl.getUserBySurName("Doe");

        assertAll("Check the return class",
                ()->assertEquals("John", userDTO.getName()),
                ()->assertEquals("Doe", userDTO.getSurname()),
                ()->assertEquals(100, userDTO.getBalance()),
                ()->assertEquals(2, userDTO.getTransactions().size()));

    }

    @Test
    void getUserBySurName_shouldReturnUser_thenNotValidReturn() throws UserNotFoundException {
        User user = User.builder().customerID(1234).initialCredit(0).name("John").surname("Doe").balance(100).account(new Account(1234, 0,
                Arrays.asList(new Transaction(1234, 1234, 100,  LocalDateTime.now()),
                        new Transaction(1235, 1234, 10, LocalDateTime.now().plusDays(1))))).build();

        when(userRepository.findUserBySurName(user.getSurname())).thenReturn(user);

        assertThrows(UserNotFoundException.class, ()->userServiceImpl.getUserBySurName("Doew"));

    }

}
