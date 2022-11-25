package com.example.test.Application.dto;

import com.example.test.Application.entity.Transaction;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * The customerDTO class
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerDTO implements Serializable {

    private Integer customerId;
    private String name;
    private String surname;
    private Integer balance;
    private List<Transaction> transactions;
}
