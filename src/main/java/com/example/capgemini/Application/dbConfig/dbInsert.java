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
                    new Transaction(7896, 123636, 0, LocalDateTime.now()),
                    new Transaction(7894, 126369, 0, LocalDateTime.now())
            ));

            List<Account> accounts = new ArrayList<>(Arrays.asList(
                    new Account(15151, 0, transactions)
            ));

            List<User> users = new ArrayList<>(Arrays.asList(
                    new User(12345, 0, "Renos", "Bardis", 100, new Account(15151, 0, transactions))));

            userRepository.saveAll(users);
            transactionRepository.saveAll(transactions);
            accountRepository.saveAll(accounts);

        };

    }
}
