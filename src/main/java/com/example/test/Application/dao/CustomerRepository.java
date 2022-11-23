package com.example.test.Application.dao;

import com.example.test.Application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * The Repository layer of User
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select p from Customer p join fetch p.account c where p.surname = :surname")
    Optional<Customer> findCustomerWithTransaction(@Param("surname") String surname);

}
