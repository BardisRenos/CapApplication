package com.example.capgemini.Application.service.Interfaces;

import com.example.capgemini.Application.dto.AccountDTO;
import com.example.capgemini.Application.dto.UserDTO;

public interface AccountService {

    AccountDTO createAccount(UserDTO userDTO);
}
