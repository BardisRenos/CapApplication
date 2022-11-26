package com.example.test.Application.mapper;

import com.example.test.Application.dto.TransactionDTO;
import com.example.test.Application.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * The dto mapper. The class that does the conversion from Transaction object into TransactionDTO objects
 */
@Service
public class TransactionMapper {

    /**
     * The conversion of the Transaction object into TransactionDTO
     * @param transaction Transaction class
     * @return TransactionDTO object
     */
    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        return new ModelMapper().map(transaction, TransactionDTO.class);
    }
}
