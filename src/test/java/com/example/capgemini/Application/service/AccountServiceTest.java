package com.example.capgemini.Application.service;


import com.example.capgemini.Application.dao.AccountRepository;
import com.example.capgemini.Application.dto.AccountDTO;
import com.example.capgemini.Application.dto.UserDetailsDTO;
import com.example.capgemini.Application.entity.Account;
import com.example.capgemini.Application.entity.Transaction;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @BeforeEach
    void setup() {
        this.accountRepository = mock(AccountRepository.class);
        this.accountServiceImpl = new AccountServiceImpl(this.accountRepository);
    }

    @Test
    void createAccount_shouldCreateAccount_thenValidReturn() {
        Account account = Account.builder().accountID(1234).initialCredit(0).transactions(Arrays.asList(
                new Transaction(5321, 1234, 100,  LocalDateTime.now()),
                new Transaction(1235, 1234, 10, LocalDateTime.now().plusDays(1)))).build();

        UserDetailsDTO userDTO = UserDetailsDTO.builder().customerID(1234).initialCredit(0).build();

        when(accountRepository.save(any(Account.class))).thenReturn(account);
        AccountDTO accountDTORes = accountServiceImpl.createAccount(userDTO);
        assertAll("Check the return entities",
                ()->assertEquals(1234, accountDTORes.getAccountID()),
                ()->assertEquals(0, accountDTORes.getInitialCredit()));
    }

    @Test
    void createAccount_shouldCreateAccount_thenNotValidReturn() {
        Account account = Account.builder().accountID(1234).initialCredit(0).transactions(Arrays.asList(
                new Transaction(5321, 1234, 100,  LocalDateTime.now()),
                new Transaction(1235, 1234, 10, LocalDateTime.now().plusDays(1)))).build();

        UserDetailsDTO userDTO = UserDetailsDTO.builder().customerID(1234).initialCredit(0).build();

        when(accountRepository.save(any(Account.class))).thenReturn(account);
        AccountDTO accountDTORes = accountServiceImpl.createAccount(userDTO);
        assertAll("Check the return entities",
                ()->assertNotEquals(123412, accountDTORes.getAccountID()),
                ()->assertNotEquals(10, accountDTORes.getInitialCredit()));
    }

}
