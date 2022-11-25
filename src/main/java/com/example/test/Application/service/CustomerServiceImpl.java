package com.example.test.Application.service;

import com.example.test.Application.mapper.CustomerMapper;
import com.example.test.Application.dao.CustomerRepository;
import com.example.test.Application.dto.CustomerDTO;
import com.example.test.Application.entity.Customer;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.exception.CustomerNotFoundException;
import com.example.test.Application.service.Interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * The Service layer of User
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO getCustomerWithTransactions(String surName) throws CustomerNotFoundException {

        Customer customer = customerRepository.findCustomerWithTransaction(surName)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("There is no customer with the surname : %s", surName)));

        CustomerDTO customerDTO = CustomerMapper.toCustomerDTO(customer);

        List<Transaction> transactionList = new ArrayList<>();
        for (int i=0; i<customer.getAccount().size(); i++) {
            transactionList.addAll(customer.getAccount().get(i).getTransactions());
        }
        customerDTO.setTransactions(transactionList);

        return customerDTO;
    }
}

