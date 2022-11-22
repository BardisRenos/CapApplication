package com.example.capgemini.Application.controller;

import com.example.capgemini.Application.dao.UserRepository;
import com.example.capgemini.Application.dto.UserDTO;
import com.example.capgemini.Application.entity.Transaction;
import com.example.capgemini.Application.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.userController = new UserController(this.userService);
    }

    @Test
    void getUser_whenCorrectSurName_thenReturnUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().name("John").surname("Doe").balance(100).transactions(Arrays.asList(
                new Transaction(1234, 6789, 100,  LocalDateTime.now()),
                new Transaction(1235, 6789, 10, LocalDateTime.now().plusDays(1)))).build();

        when(userService.getUserBySurName("Doe")).thenReturn(userDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getUserBySurName?surname="+userDTO.getSurname())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(userDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", is(userDTO.getSurname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance", is(userDTO.getBalance())))
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        UserDTO myObjectsRes = new ObjectMapper().readValue(jsonResponse, UserDTO.class);

        assertNotNull(myObjectsRes);
        assertEquals("John", myObjectsRes.getName());
        assertEquals("Doe", myObjectsRes.getSurname());
        assertEquals(2, myObjectsRes.getTransactions().size());
    }

}
