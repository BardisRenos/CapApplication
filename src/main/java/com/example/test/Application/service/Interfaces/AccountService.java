package com.example.test.Application.service.Interfaces;

import com.example.test.Application.dto.AccountDTO;
import com.example.test.Application.exception.CustomerNotFoundException;
import com.example.test.Application.request.CreateAccountRequest;

/**
 * The Account Service Interface. All methods that Account Service has.
 */
public interface AccountService {

    AccountDTO createAccount(CreateAccountRequest createAccountRequest) throws CustomerNotFoundException;
}
