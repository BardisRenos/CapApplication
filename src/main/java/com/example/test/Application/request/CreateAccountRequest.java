package com.example.test.Application.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The request class for a new current account.
 */
@Getter
@Setter
@Builder
public class CreateAccountRequest {

    @NotNull
    private Integer customerID;
    @NotNull
    @Min(value = 0, message = "Initial Credit value must not be negative value")
    private Integer initialCredit;

    public Integer getInitialCredit() {
        return initialCredit;
    }

    public Integer getCustomerID() {
        return customerID;
    }
}
