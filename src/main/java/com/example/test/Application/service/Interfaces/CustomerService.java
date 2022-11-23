package com.example.test.Application.service.Interfaces;

import com.example.test.Application.dto.CustomerDTO;
import com.example.test.Application.exception.CustomerNotFoundException;

/**
 * The User Service Interface. All methods that User Service has.
 */
public interface CustomerService {

    CustomerDTO getCustomerWithTransactions(String surname) throws CustomerNotFoundException;
}
