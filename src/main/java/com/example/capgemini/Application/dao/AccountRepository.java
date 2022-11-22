package com.example.capgemini.Application.dao;

import com.example.capgemini.Application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Repository layer of Account
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
