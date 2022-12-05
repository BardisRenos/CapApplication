package com.example.test.Application.service;

import com.example.test.Application.dao.AccountRepository;
import com.example.test.Application.dto.AccountDTO;
import com.example.test.Application.entity.Account;
import com.example.test.Application.entity.Customer;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.exception.CustomerNotFoundException;
import com.example.test.Application.mapper.AccountMapper;
import com.example.test.Application.request.CreateAccountRequest;
import com.example.test.Application.service.Interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * The Service layer of Account
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final CustomerServiceImpl customerService;

    /**
     * This method creates a new account (First checks if the customer exists)
     * @param createAccountRequest The given object that contains the Customer ID and the initial value
     * @return An AccountDTO class
     * @throws CustomerNotFoundException If there is no customer with a given id then an exception rises
     */
    @Override
    public AccountDTO createAccount(CreateAccountRequest createAccountRequest) throws CustomerNotFoundException {

        Customer customer = customerService.getCustomerById(createAccountRequest.getCustomerID());

        Account account = new Account();
        account.setInitialCredit(createAccountRequest.getInitialCredit());
        account.setCustomer(customer);
        account.setDateCreation(LocalDateTime.now());

            if (createAccountRequest.getInitialCredit() > 0) {
                Transaction transaction = new Transaction();
                transaction.setAmount(account.getInitialCredit());
                transaction.setTime(LocalDateTime.now());

                account.getTransactions().add(transaction);

                return AccountMapper.toAccountTransactionDTO(accountRepository.save(account));
            }

        return AccountMapper.toAccountDTO(accountRepository.save(account));
    }
}
