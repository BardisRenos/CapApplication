package com.example.test.Application.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionDTO implements Serializable {

    private Integer transactionID;
    private Integer customerID;
    private Integer amount;
    private LocalDateTime time;
}
