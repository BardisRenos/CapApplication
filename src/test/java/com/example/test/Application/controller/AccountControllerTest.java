package com.example.test.Application.controller;

import com.example.test.Application.dao.CustomerRepository;
import com.example.test.Application.dto.AccountDTO;
import com.example.test.Application.dto.AccountTransactionDTO;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.request.CreateAccountRequest;
import com.example.test.Application.service.AccountServiceImpl;
import com.example.test.Application.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(controllers = AccountController.class)
@RunWith(SpringJUnit4ClassRunner.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerServiceImpl customerService;

    @InjectMocks
    AccountController accountController;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.accountController = new AccountController(this.accountService);
    }

    @Test
    void testCreateCurrentAccount_whenInitialCreditIsZero_shouldReturnAccountDto() throws Exception {

        CreateAccountRequest request = CreateAccountRequest.builder().customerID(1).initialCredit(0).build();
        AccountDTO accountDTO = AccountDTO.builder().accountID(1).initialCredit(0).dateCreation(LocalDateTime.now()).build();

        when(accountService.createAccount(any(CreateAccountRequest.class))).thenReturn(accountDTO);

        AccountDTO accountDtoRes = accountController.createCurrentAccount(request);

        assertAll("Check the return entities",
                ()->assertEquals(1, accountDtoRes.getAccountID()),
                ()->assertEquals(0, accountDtoRes.getInitialCredit()),
                ()->assertNotNull(accountDtoRes));
    }

    @Test
    void testCreateCurrentAccount_whenInitialCreditIsTen_shouldReturnAccountTransactionDto() throws Exception {

        CreateAccountRequest request = CreateAccountRequest.builder().customerID(1).initialCredit(10).build();
        AccountTransactionDTO accountTransactionDTO =
                new AccountTransactionDTO(10, 10, 90, LocalDateTime.now(),
                        List.of(new Transaction(1, 10, LocalDateTime.now())));

        when(accountService.createAccount(any(CreateAccountRequest.class))).thenReturn(accountTransactionDTO);

        AccountTransactionDTO accountDTO = (AccountTransactionDTO) accountController.createCurrentAccount(request);

        assertAll("Check the return entities",
                ()->assertEquals(10, accountDTO.getAccountID()),
                ()->assertEquals(10, accountDTO.getAccountID()),
                ()->assertEquals(1, accountTransactionDTO.getTransactions().size()),
                ()->assertEquals(1, accountTransactionDTO.getTransactions().get(0).getTransactionID()));

    }
}
