package com.example.capgemini.Application.service.Interfaces;

import com.example.capgemini.Application.dto.AccountDTO;
import com.example.capgemini.Application.dto.UserDetailsDTO;

/**
 * The Account Service Interface. All methods that Account Service has.
 */
public interface AccountService {

    AccountDTO createAccount(UserDetailsDTO userDTO);
}
