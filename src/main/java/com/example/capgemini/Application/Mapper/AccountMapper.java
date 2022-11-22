package com.example.capgemini.Application.Mapper;

import com.example.capgemini.Application.dto.AccountDTO;
import com.example.capgemini.Application.entity.Account;
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
}
