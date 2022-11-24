package com.example.test.Application.Mapper;

import com.example.test.Application.dto.AccountDTO;
import com.example.test.Application.dto.AccountTransactionDTO;
import com.example.test.Application.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * The dto mapper. The class that does the conversion from Account object into AccountDTO object
 */
@Service
public class AccountMapper {

    /**
     * The conversion of the Account object into AccountDTO
     * @param account account class
     * @return AccountDTO class
     */
    public static AccountDTO toAccountDTO(Account account) {
        return new ModelMapper().map(account, AccountDTO.class);
    }

    /**
     * The conversion of the Account into AccountTransactionDTO
     * @param account account class
     * @return AccountTransactionDTO class
     */
    public static AccountTransactionDTO toAccountTransactionDTO(Account account) {
        return new ModelMapper().map(account, AccountTransactionDTO.class);
    }

}
