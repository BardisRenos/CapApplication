package com.example.test.Application.dao;

import com.example.test.Application.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Repository layer of Transaction
 */
@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
}
