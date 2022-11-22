package com.example.capgemini.Application.dto;

import lombok.*;

/**
 * The UserDetailsDTO class
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDetailsDTO {

    private Integer customerID;
    private Integer initialCredit;
}
