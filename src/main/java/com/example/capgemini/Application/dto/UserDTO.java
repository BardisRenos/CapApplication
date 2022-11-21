package com.example.capgemini.Application.dto;

import com.example.capgemini.Application.entity.Transaction;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO implements Serializable {

    private Integer customerID;
    private Integer initialCredit;
    private String name;
    private String surname;
    private Integer balance;
    private List<Transaction> transactions;
}
