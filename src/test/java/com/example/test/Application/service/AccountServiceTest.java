package com.example.test.Application.service;


import com.example.test.Application.dao.AccountRepository;
import com.example.test.Application.dto.AccountDTO;
import com.example.test.Application.dto.AccountTransactionDTO;
import com.example.test.Application.entity.Account;
import com.example.test.Application.entity.Customer;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.exception.CustomerNotFoundException;
import com.example.test.Application.exception.NotSufficientFundException;
import com.example.test.Application.request.CreateAccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private CustomerServiceImpl customerService;

    @Autowired
    private AccountServiceImpl accountServiceImpl;


    @BeforeEach
    void setup() {
        this.accountRepository = mock(AccountRepository.class);
        this.accountServiceImpl = new AccountServiceImpl(this.accountRepository, this.customerService);
    }

//    @Test
//    void testCreateAccount_whenInitialCreditIsZero_shouldReturnAccountDto() throws CustomerNotFoundException, NotSufficientFundException {
//        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder().customerID(1).initialCredit(0).build();
//
//        Account account = Account.builder().accountID(1).initialCredit(100).dateCreation(LocalDateTime.now()).build();
//        Customer customer = Customer.builder().customerID(1).name("Renos").surname("Bardis").balance(100).build();
//
//        when(customerService.getCustomerById(createAccountRequest.getCustomerID())).thenReturn(customer);
//        when(accountRepository.save(any(Account.class))).thenReturn(account);
//        AccountDTO accountDtoRes = accountServiceImpl.createAccount(createAccountRequest);
//
//        assertAll("Check the return entities",
//                ()->assertEquals(1, accountDtoRes.getAccountID()),
//                ()->assertEquals(100, accountDtoRes.getInitialCredit()));
//    }

    @Test
    void testCreateAccount_whenInitialCreditIsTen_thenReturnAccountTransactionDTO() throws CustomerNotFoundException, NotSufficientFundException {
        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder().customerID(1).initialCredit(10).build();

        Account account = new Account(1, 10, LocalDateTime.now());
        Customer customer = Customer.builder().customerID(1).name("Renos").surname("Bardis").balance(100).build();

        account.setCustomer(customer);
        Transaction transaction = new Transaction();
        transaction.setAmount(account.getInitialCredit());
        transaction.setTime(LocalDateTime.now());
        account.getTransactions().add(transaction);

        when(customerService.getCustomerById(createAccountRequest.getCustomerID())).thenReturn(customer);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountTransactionDTO accountDtoRes = (AccountTransactionDTO) accountServiceImpl.createAccount(createAccountRequest);

        assertAll("Check the return entities",
                ()->assertEquals(1, accountDtoRes.getAccountID()),
                ()->assertEquals(10, accountDtoRes.getInitialCredit()),
                ()->assertEquals(10, accountDtoRes.getTransactions().get(0).getAmount()),
                ()->assertEquals(1, accountDtoRes.getTransactions().size()),
                ()->assertEquals(10, accountDtoRes.getTransactions().get(0).getAmount()));
    }

    @Test
    void testCreateAccount_whenTheCustomerDoesNotExists_thenReturnCustomerNotFoundException() throws CustomerNotFoundException {
        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder().customerID(11).initialCredit(0).build();

        when(customerService.getCustomerById(11)).thenThrow(new CustomerNotFoundException("Not found"));
        when(accountRepository.save(any(Account.class))).thenReturn(null);

        assertThrows(CustomerNotFoundException.class, ()-> accountServiceImpl.createAccount(createAccountRequest));
    }

}
