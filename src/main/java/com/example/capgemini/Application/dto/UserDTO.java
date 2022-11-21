package com.example.capgemini.Application.dto;

import lombok.*;

import java.io.Serializable;

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
}
