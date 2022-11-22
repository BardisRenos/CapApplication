package com.example.capgemini.Application.controller;

import com.example.capgemini.Application.dto.UserDTO;
import com.example.capgemini.Application.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The Controller layer of User
 */
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("getUserBySurName")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@RequestParam(value = "surname") String surname) {
        return userService.getUserBySurName(surname);
    }
}
