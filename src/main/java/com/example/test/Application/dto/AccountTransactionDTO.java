package com.example.test.Application.dto;

import com.example.test.Application.entity.Transaction;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The AccountTransactionDTO class
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountTransactionDTO extends AccountDTO {

    private List<Transaction> transactions;

    public AccountTransactionDTO(Integer accountID, Integer initialCredit, LocalDateTime dateCreation, List<Transaction> transactions) {
        super(accountID, initialCredit, dateCreation);
        this.transactions = transactions;
    }
}
