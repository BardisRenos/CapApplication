package com.example.capgemini.Application.service.validation;

import com.example.capgemini.Application.entity.User;
import com.example.capgemini.Application.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CustomerValidation {

    public void validate(Optional<User> user) throws UserNotFoundException {
        User userCheck = null;

        if (user.isPresent()) {
            userCheck = user.get();
        }

        if (Objects.isNull(userCheck)) {
            throw new UserNotFoundException("Customer not found with the customer id ");
        }
    }
}
