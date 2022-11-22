package com.example.capgemini.Application.controller;

import com.example.capgemini.Application.dto.AccountDTO;
import com.example.capgemini.Application.dto.UserDetailsDTO;
import com.example.capgemini.Application.exception.UserNotFoundException;
import com.example.capgemini.Application.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The Controller layer of Account
 */
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping("createAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createCurrentAccount(@RequestBody UserDetailsDTO userDTO) throws UserNotFoundException {
        return accountService.createAccount(userDTO);
    }
}
