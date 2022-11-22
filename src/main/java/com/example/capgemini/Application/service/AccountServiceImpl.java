package com.example.capgemini.Application.service;

import com.example.capgemini.Application.Mapper.AccountMapper;
import com.example.capgemini.Application.Mapper.UserMapper;
import com.example.capgemini.Application.dao.AccountRepository;
import com.example.capgemini.Application.dto.AccountDTO;
import com.example.capgemini.Application.dto.UserDetailsDTO;
import com.example.capgemini.Application.entity.Account;
import com.example.capgemini.Application.entity.Transaction;
import com.example.capgemini.Application.entity.User;
import com.example.capgemini.Application.service.Interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;


/**
 * The Service layer of Account
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;


    @Override
    public AccountDTO createAccount(UserDetailsDTO userDTO) {
        Account account = new Account();
        User user = UserMapper.toUserDetailsEntity(userDTO);

        if (user.getInitialCredit() != 0) {
            Integer randomNumber = new Random().nextInt(9000) + 1000;

            account.setAccountID(user.getCustomerID());
            account.setInitialCredit(user.getInitialCredit());
            account.setTransactions(Arrays.asList(new Transaction(randomNumber, user.getCustomerID(), user.getInitialCredit(), LocalDateTime.now())));

        } else {
            account.setAccountID(user.getCustomerID());
            account.setInitialCredit(user.getInitialCredit());

        }
        account = accountRepository.save(account);
        return AccountMapper.toAccountDTO(account);
    }
}
