package com.example.test.Application.dto;

import lombok.*;

import java.io.Serializable;

/**
 * The CustomerDTO class
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerDTO implements Serializable {

    private Integer customerId;
    private String name;
    private String surname;
    private Integer balance;
}
