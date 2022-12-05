package com.example.test.Application.dto;

import com.example.test.Application.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The CustomerTransactionDTO class
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerTransactionDTO extends CustomerDTO {

    private List<Transaction> transactions;

    public CustomerTransactionDTO(Integer customerId, String name, String surname, Integer balance, List<Transaction> transactions) {
        super(customerId, name, surname, balance);
        this.transactions = transactions;
    }
}
