package com.example.test.Application.controller;

import com.example.test.Application.dto.AccountDTO;
import com.example.test.Application.exception.CustomerNotFoundException;
import com.example.test.Application.request.CreateAccountRequest;
import com.example.test.Application.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The Controller layer of Account
 */
@RestController
@RequestMapping(value = "/api/v1/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping("account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createCurrentAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) throws CustomerNotFoundException {
        return accountService.createAccount(createAccountRequest);
    }
}
