package com.example.capgemini.Application.service.Interfaces;

import com.example.capgemini.Application.dto.UserDTO;

/**
 * The User Service Interface. All methods that User Service has.
 */
public interface UserService {

    UserDTO getUserBySurName(String surName);
}
