package com.banking.Banking.Application.services;

import com.banking.Banking.Application.dto.AccountDto;

import java.util.List;

public interface AccountServices {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto depositAccount(Long id , double balance);
    AccountDto withdrawAccount(Long id , double balance);
    List<AccountDto> getAllAccount();
    String deleteAccount(Long id);
    AccountDto updateAccount(Long id ,AccountDto accountDto);
}
