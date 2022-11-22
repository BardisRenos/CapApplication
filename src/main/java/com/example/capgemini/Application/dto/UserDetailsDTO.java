package com.example.capgemini.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * The UserDetailsDTO class
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDetailsDTO {

    private Integer customerID;
    private Integer initialCredit;
}
