package com.example.capgemini.Application.Mapper;

import com.example.capgemini.Application.dto.AccountDTO;
import com.example.capgemini.Application.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public static AccountDTO toAccountDTO(Account account) {
        return new ModelMapper().map(account, AccountDTO.class);
    }

    public static Account toAccountEntity(AccountDTO accountDTO) {
        return new ModelMapper().map(accountDTO, Account.class);
    }
}
