package com.example.test.Application.service;

import com.example.test.Application.dao.AccountRepository;
import com.example.test.Application.dto.AccountDTO;
import com.example.test.Application.dto.AccountTransactionDTO;
import com.example.test.Application.entity.Account;
import com.example.test.Application.entity.Customer;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.exception.CustomerNotFoundException;
import com.example.test.Application.exception.NotSufficientFundException;
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
     */
    @Override
    public AccountDTO createAccount(CreateAccountRequest createAccountRequest) throws CustomerNotFoundException, NotSufficientFundException {

        Customer customer = customerService.getCustomerById(createAccountRequest.getCustomerID());
        Integer balance = customer.getBalance();

        Account account = new Account();
        checkAmount(customer.getBalance(), createAccountRequest.getInitialCredit());

        customer = customerService.updateBalance(customer, createAccountRequest);

        account.setInitialCredit(createAccountRequest.getInitialCredit());
        account.setCustomer(customer);
        account.setDateCreation(LocalDateTime.now());

            if (createAccountRequest.getInitialCredit() > 0) {
                Transaction transaction = new Transaction();
                transaction.setAmount(account.getInitialCredit());
                transaction.setTime(LocalDateTime.now());

                account.getTransactions().add(transaction);

                AccountTransactionDTO accountTransactionDTO = AccountMapper.toAccountTransactionDTO(accountRepository.save(account));
                accountTransactionDTO.setNewBalance(balance-createAccountRequest.getInitialCredit());
                return accountTransactionDTO;
            }

        AccountDTO accountDTO = AccountMapper.toAccountDTO(accountRepository.save(account));
        accountDTO.setNewBalance(customer.getBalance()-createAccountRequest.getInitialCredit());
        return accountDTO;
    }

    private void checkAmount(Integer balance, Integer initialCredit) throws NotSufficientFundException {

        if (balance<initialCredit) {
            throw new NotSufficientFundException(String.format("The balance is not sufficient for the amount %s", initialCredit));
        }
    }
}
