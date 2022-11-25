package com.example.test.Application.controller;

import com.example.test.Application.dao.AccountRepository;
import com.example.test.Application.dao.CustomerRepository;
import com.example.test.Application.dto.CustomerDTO;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@RunWith(SpringJUnit4ClassRunner.class)
class CustomerControllerTest {


    public static final String CUSTOMER_API_ENDPOINT_FIRST_END_POINT = "/api/v1/customer?surname=";

    public static final String CUSTOMER_API_ENDPOINT_SECOND_END_POINT = "/api/v1/customers";
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
    void testGetCustomerWithTransactions_whenCustomerSurNameExists_shouldReturnCustomerTransactionDto() throws Exception {
        CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO(1, "Renos", "Bardis", 100,
                List.of(new Transaction(10, 100, LocalDateTime.now()), new Transaction(11, 200, LocalDateTime.now())));

        when(customerService.getCustomerWithTransactions("Bardis")).thenReturn(customerTransactionDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_API_ENDPOINT_FIRST_END_POINT+"Bardis")
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

    @Test
    void testGetAllCustomers_whenAllCustomersExists_shouldReturnCustomerDtoList() throws Exception {
        List<CustomerDTO> customersDTO = new ArrayList<>(Arrays.asList(
                new CustomerDTO(1, "Renos", "Bardis", 100),
                new CustomerDTO(2, "John", "Doe", 50),
                new CustomerDTO(3, "Nick", "Smith", 45)));

        when(customerService.getAllCustomers()).thenReturn(customersDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_API_ENDPOINT_SECOND_END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(customersDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", hasSize(customersDTO.size())))
                .andExpect(jsonPath("$[0].name", is(customersDTO.get(0).getName())))
                .andExpect(jsonPath("$[0].surname", is(customersDTO.get(0).getSurname())))
                .andExpect(jsonPath("$[0].balance", is(customersDTO.get(0).getBalance())))
                .andExpect(jsonPath("$[1].name", is(customersDTO.get(1).getName())))
                .andExpect(jsonPath("$[1].surname", is(customersDTO.get(1).getSurname())))
                .andExpect(jsonPath("$[1].balance", is(customersDTO.get(1).getBalance())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        List<CustomerDTO> customerRes = om.readValue(jsonResponse, om.getTypeFactory().constructCollectionType(List.class, CustomerDTO.class));

        assertNotNull(customerRes);
        assertEquals("Renos", customerRes.get(0).getName());
        assertEquals("Bardis", customerRes.get(0).getSurname());
        assertEquals("John", customerRes.get(1).getName());
        assertEquals("Doe", customerRes.get(1).getSurname());
    }



}
