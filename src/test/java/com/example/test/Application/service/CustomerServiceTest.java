package com.example.test.Application.service;


import com.example.test.Application.dao.CustomerRepository;
import com.example.test.Application.dto.CustomerTransactionDTO;
import com.example.test.Application.entity.Account;
import com.example.test.Application.entity.Customer;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.exception.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setup() {
        this.customerRepository = mock(CustomerRepository.class);
        this.customerServiceImpl = new CustomerServiceImpl(this.customerRepository);
    }

    @Test
    void testGetCustomerBySurName_shouldReturnCustomer_thenReturnCustomerDto() throws CustomerNotFoundException {
        Customer customer = Customer.builder().customerID(1234).name("John").surname("Doe").balance(100).build();
        Account account = Account.builder().accountID(1).initialCredit(100).dateCreation(LocalDateTime.now()).build();
        Transaction transaction = Transaction.builder().transactionID(1).amount(10).time(LocalDateTime.now()).build();

        account.setTransactions(List.of(transaction));
        customer.setAccount(List.of(account));

        when(customerRepository.findCustomerWithTransaction(customer.getSurname())).thenReturn(Optional.of(customer));
        CustomerTransactionDTO customerTransactionDTO = customerServiceImpl.getCustomerWithTransactions("Doe");

        assertAll("Check the return class",
                ()->assertEquals(1234, customerTransactionDTO.getCustomerId()),
                ()->assertEquals("John", customerTransactionDTO.getName()),
                ()->assertEquals("Doe", customerTransactionDTO.getSurname()),
                ()->assertEquals(100, customerTransactionDTO.getBalance()),
                ()->assertEquals(1, customerTransactionDTO.getTransactions().size()));
    }

    @Test
    void testGetCustomerBySurName_shouldNotReturnCustomer_thenCustomerNotFoundException() {
        Customer customer = Customer.builder().customerID(1234).name("John").surname("Doe").balance(100).build();
        Account account = Account.builder().accountID(1).initialCredit(100).dateCreation(LocalDateTime.now()).build();
        Transaction transaction = Transaction.builder().transactionID(1).amount(10).time(LocalDateTime.now()).build();

        account.setTransactions(List.of(transaction));
        customer.setAccount(List.of(account));

        when(customerRepository.findCustomerWithTransaction(customer.getSurname())).thenReturn(Optional.of(customer));

        assertThrows(CustomerNotFoundException.class, ()-> customerServiceImpl.getCustomerWithTransactions("Doew"));
    }

}
