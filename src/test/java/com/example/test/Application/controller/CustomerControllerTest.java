package com.example.test.Application.controller;

import com.example.test.Application.dao.AccountRepository;
import com.example.test.Application.dao.CustomerRepository;
import com.example.test.Application.dto.CustomerDTO;
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
    void testGetCustomerWithTransactions_whenCustomerIdExists_shouldReturnCustomerDto() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().name("Renos").surname("Bardis").balance(100)
                .transactions(List.of(new Transaction(10, 100, LocalDateTime.now()),
                        new Transaction(11, 200, LocalDateTime.now()))).build();

        when(customerService.getCustomerWithTransactions("Bardis")).thenReturn(customerDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_API_ENDPOINT+"Bardis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(customerDTO.getName())))
                .andExpect(jsonPath("$.balance", is(customerDTO.getBalance())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        CustomerDTO customerDtoRes = new ObjectMapper().readValue(jsonResponse, CustomerDTO.class);

        assertNotNull(customerDtoRes);
        assertEquals("Renos", customerDtoRes.getName());
        assertEquals("Bardis", customerDtoRes.getSurname());
        assertEquals(2, customerDtoRes.getTransactions().size());
        assertEquals(100, customerDtoRes.getTransactions().get(0).getAmount());
    }


}
