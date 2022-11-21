package com.example.capgemini.Application.service;

import com.example.capgemini.Application.dao.AccountRepository;
import com.example.capgemini.Application.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        log.info();
        return null;
    }
}
