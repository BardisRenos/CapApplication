package com.example.capgemini.Application.controller;

import com.example.capgemini.Application.dto.AccountDTO;
import com.example.capgemini.Application.dto.TransactionDTO;
import com.example.capgemini.Application.dto.UserDetailsDTO;
import com.example.capgemini.Application.entity.Account;
import com.example.capgemini.Application.entity.Transaction;
import com.example.capgemini.Application.entity.User;
import com.example.capgemini.Application.service.AccountServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountServiceImpl accountService;

    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.accountController = new AccountController(this.accountService);
    }

    @Test
    void createCurrentAccount_whenInitialCredit_is_0_thenReturnAccount() throws Exception {
        AccountDTO accountDTO = AccountDTO.builder().accountID(12340).initialCredit(0).build();

        when(accountService.createAccount(any(UserDetailsDTO.class))).thenReturn(accountDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/createAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(accountDTO)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountID", is(accountDTO.getAccountID())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.initialCredit", is(accountDTO.getInitialCredit())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        AccountDTO myObjectsRes = new ObjectMapper().readValue(jsonResponse, AccountDTO.class);

        assertNotNull(myObjectsRes);
        assertEquals(12340, myObjectsRes.getAccountID());
        assertEquals(0, myObjectsRes.getInitialCredit());
    }

    @Test
    void createCurrentAccount_whenInitialCredit_is_1_thenReturnAccount() throws Exception {
        AccountDTO accountDTO = AccountDTO.builder().accountID(1234).initialCredit(1).build();

        TransactionDTO transactionDTO = TransactionDTO.builder().transactionID(98760).customerID(1234)
                .amount(1).time(LocalDateTime.now()).build();

        Account account = Account.builder().accountID(1234).initialCredit(1).transactions(Arrays.asList(
                new Transaction(4321, 1234, 100,  LocalDateTime.now()),
                new Transaction(4322, 1234, 10, LocalDateTime.now().plusDays(1)))).build();

        User user = new User(1234, 1, "John", "Doe", 100, account);

        when(accountService.createAccount(any(UserDetailsDTO.class))).thenReturn(accountDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/createAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(accountDTO)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountID", is(accountDTO.getAccountID())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.initialCredit", is(accountDTO.getInitialCredit())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        AccountDTO myObjectsRes = new ObjectMapper().readValue(jsonResponse, AccountDTO.class);

        assertNotNull(myObjectsRes);
        assertEquals(1234, myObjectsRes.getAccountID());
        assertEquals(1, myObjectsRes.getInitialCredit());
    }

}
