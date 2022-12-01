package com.example.test.Application.service;

import com.example.test.Application.dto.CustomerDTO;
import com.example.test.Application.mapper.CustomerMapper;
import com.example.test.Application.dao.CustomerRepository;
import com.example.test.Application.dto.CustomerTransactionDTO;
import com.example.test.Application.entity.Customer;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.exception.CustomerNotFoundException;
import com.example.test.Application.service.Interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The Service layer of User
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerTransactionDTO getCustomerWithTransactions(String surName) throws CustomerNotFoundException {

        Customer customer = customerRepository.findCustomerWithTransaction(surName)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("There is no transactions with the surname : %s", surName)));

        CustomerTransactionDTO customerTransactionDTO = CustomerMapper.toCustomerTransactionDTO(customer);

        List<Transaction> transactionList = new ArrayList<>();
        for (int i=0; i<customer.getAccount().size(); i++) {
            transactionList.addAll(customer.getAccount().get(i).getTransactions());
        }
        customerTransactionDTO.setTransactions(transactionList);

        return customerTransactionDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toCustomerDTO).collect(Collectors.toList());
    }
}

