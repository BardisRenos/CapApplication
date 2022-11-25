package com.example.test.Application.service;

import com.example.test.Application.mapper.AccountMapper;
import com.example.test.Application.dao.AccountRepository;
import com.example.test.Application.dao.CustomerRepository;
import com.example.test.Application.dto.AccountDTO;
import com.example.test.Application.entity.Account;
import com.example.test.Application.entity.Customer;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.exception.CustomerNotFoundException;
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

    private final CustomerRepository customerRepository;

    @Override
    public AccountDTO createAccount(CreateAccountRequest createAccountRequest) throws CustomerNotFoundException {

        Customer customer = getCustomer(createAccountRequest.getCustomerID());

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

    private Customer getCustomer(Integer id) throws CustomerNotFoundException {

        return customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("There is no customer with the id : %d", id)));
    }
}
