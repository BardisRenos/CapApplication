package com.example.test.Application.service;

import com.example.test.Application.dao.CustomerRepository;
import com.example.test.Application.dto.CustomerDTO;
import com.example.test.Application.dto.CustomerTransactionDTO;
import com.example.test.Application.entity.Customer;
import com.example.test.Application.entity.Transaction;
import com.example.test.Application.exception.CustomerNotFoundException;
import com.example.test.Application.mapper.CustomerMapper;
import com.example.test.Application.request.CreateAccountRequest;
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

    /**
     * This method get a specific customer with all his transactions
     * @param surName The surname of the customer entity
     * @return A CustomerTransactionDTO class
     * @throws CustomerNotFoundException If there is no transactions with a given surname then an exception rises
     */
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

    /**
     * This method returns all customers from the database
     * @return A list of CustomerDTO entities
     */
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toCustomerDTO).collect(Collectors.toList());
    }

    /**
     * This method returns a customer
     * @param id The given customer ID
     * @return A Customer entity
     * @throws CustomerNotFoundException If there is no customer with a given id then an exception rises
     */
    protected Customer getCustomerById(Integer id) throws CustomerNotFoundException {

        return customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(
                        String.format("There is no customer with the id : %d", id)));
    }

    protected Customer updateBalance(Customer customer, CreateAccountRequest createAccountRequest) {

        customer.setBalance(customer.getBalance() - createAccountRequest.getInitialCredit());
        return  customer;
    }
}

