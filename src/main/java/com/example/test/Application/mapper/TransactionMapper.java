package com.example.test.Application.mapper;

import com.example.test.Application.dto.TransactionDTO;
import com.example.test.Application.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        return new ModelMapper().map(transaction, TransactionDTO.class);
    }
}
