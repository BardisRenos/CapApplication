package com.example.capgemini.Application.dto;

import com.example.capgemini.Application.entity.Transaction;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountDTO implements Serializable {

    private Integer accountID;
    private Integer initialCredit;
    private Transaction transaction;
}
