package com.example.capgemini.Application.service.Interfaces;

import com.example.capgemini.Application.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUsers(String surName);
}
