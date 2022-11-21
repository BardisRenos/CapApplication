package com.example.capgemini.Application.Mapper;

import com.example.capgemini.Application.dto.TransactionDTO;
import com.example.capgemini.Application.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        return new ModelMapper().map(transaction, TransactionDTO.class);
    }
}
