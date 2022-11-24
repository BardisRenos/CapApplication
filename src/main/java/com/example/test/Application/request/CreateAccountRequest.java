package com.example.test.Application.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * The request class for a new current account.
 */
@Getter
@Setter
@Builder
public class CreateAccountRequest {

    @NonNull
    private Integer customerID;
    @Min(value = 0, message = "Initial Credit value must not be negative value")
    private Integer initialCredit;
}
