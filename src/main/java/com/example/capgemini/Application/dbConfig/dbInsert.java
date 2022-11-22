package com.example.capgemini.Application.dbConfig;


import com.example.capgemini.Application.dao.AccountRepository;
import com.example.capgemini.Application.dao.TransactionRepository;
import com.example.capgemini.Application.dao.UserRepository;
import com.example.capgemini.Application.entity.Account;
import com.example.capgemini.Application.entity.Transaction;
import com.example.capgemini.Application.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class dbInsert {

    @Bean
    public CommandLineRunner initDB(UserRepository userRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {

        return args -> {

            List<Transaction> transactions = new ArrayList<>(Arrays.asList(
                    new Transaction(7896, 1234, 10, LocalDateTime.now()),
                    new Transaction(7894, 1234, 20, LocalDateTime.now().plusDays(1)),
                    new Transaction(9876, 2345, 50, LocalDateTime.now().plusDays(5))));

            List<Account> accounts = new ArrayList<>(Arrays.asList(
                    new Account(1234, 0, transactions.subList(0, 2)),
                    new Account(2345, 0, transactions.subList(2, 3))
            ));

            List<User> users = new ArrayList<>(Arrays.asList(
                    new User(1234, 0, "Renos", "Bardis", 100, new Account(1234, 0, transactions.subList(0,2))),
                    new User(2345, 0, "John", "Doe", 50, new Account(2345, 0, transactions.subList(2, 3)))));

            userRepository.saveAll(users);
            transactionRepository.saveAll(transactions);
            accountRepository.saveAll(accounts);

        };

    }
}
