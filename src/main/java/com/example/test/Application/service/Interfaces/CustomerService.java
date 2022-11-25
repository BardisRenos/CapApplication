package com.example.test.Application.service.Interfaces;

import com.example.test.Application.dto.CustomerDTO;
import com.example.test.Application.dto.CustomerTransactionDTO;
import com.example.test.Application.exception.CustomerNotFoundException;

import java.util.List;

/**
 * The User Service Interface. All methods that User Service has.
 */
public interface CustomerService {

    CustomerTransactionDTO getCustomerWithTransactions(String surname) throws CustomerNotFoundException;

    List<CustomerDTO> getAllCustomers();
}
