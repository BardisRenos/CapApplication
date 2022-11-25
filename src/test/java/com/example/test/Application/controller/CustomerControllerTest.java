package com.example.test.Application.controller;

import com.example.test.Application.dao.AccountRepository;
import com.example.test.Application.dao.CustomerRepository;
import com.example.test.Application.dto.CustomerTransactionDTO;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.service.AccountServiceImpl;
import com.example.test.Application.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@RunWith(SpringJUnit4ClassRunner.class)
class CustomerControllerTest {


    public static final String CUSTOMER_API_ENDPOINT = "/api/v1/customer?surname=";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    @MockBean
    private AccountController accountController;

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private com.example.test.Application.dbConfig.dbInsert dbInsert;

    ObjectMapper om = new ObjectMapper();

    @Test
    void testGetCustomerWithTransactions_whenCustomerSurNameExists_shouldReturnCustomerDto() throws Exception {
        CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO(1, "Renos", "Bardis", 100,
                List.of(new Transaction(10, 100, LocalDateTime.now()), new Transaction(11, 200, LocalDateTime.now())));

        when(customerService.getCustomerWithTransactions("Bardis")).thenReturn(customerTransactionDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_API_ENDPOINT+"Bardis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(customerTransactionDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(customerTransactionDTO.getName())))
                .andExpect(jsonPath("$.balance", is(customerTransactionDTO.getBalance())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        CustomerTransactionDTO customerTransactionDtoRes = new ObjectMapper().readValue(jsonResponse, CustomerTransactionDTO.class);

        assertNotNull(customerTransactionDtoRes);
        assertEquals("Renos", customerTransactionDtoRes.getName());
        assertEquals("Bardis", customerTransactionDtoRes.getSurname());
        assertEquals(2, customerTransactionDtoRes.getTransactions().size());
        assertEquals(100, customerTransactionDtoRes.getTransactions().get(0).getAmount());
    }


}
