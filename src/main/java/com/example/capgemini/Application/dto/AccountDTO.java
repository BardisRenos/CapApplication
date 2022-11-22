package com.example.capgemini.Application.dto;

import lombok.*;

import java.io.Serializable;

/**
 * The AccountDTO class
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountDTO implements Serializable {

    private Integer accountID;
    private Integer initialCredit;
}
