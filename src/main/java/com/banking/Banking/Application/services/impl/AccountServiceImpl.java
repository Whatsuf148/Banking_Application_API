package com.banking.Banking.Application.services.impl;

import com.banking.Banking.Application.dto.AccountDto;
import com.banking.Banking.Application.entity.Account;
import com.banking.Banking.Application.mapper.AccountMapper;
import com.banking.Banking.Application.repository.AccountRepository;
import com.banking.Banking.Application.services.AccountServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AccountServiceImpl implements AccountServices {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override

    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return  AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("" +
                "Account Id doesn't exit"));
        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto depositAccount(Long id, double balance) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("" +
                "Account Id doesn't exit"));
        double ammount = account.getBalance()+ balance;
        account.setBalance(ammount);
        Account saved = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(saved);
    }

    @Override
    public AccountDto withdrawAccount(Long id, double balance) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException(
                "Account Id doesn't exit"));
        if(account.getBalance()<balance){
            throw new RuntimeException("You don't have sufficient balance");
        }
        double amount = account.getBalance() - balance;
        account.setBalance(amount);
        Account saved = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(saved);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map((AccountMapper::maptoAccountDto)).toList();

    }

    @Override
    public String deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException(
                "This ID doesn't exit"));
        accountRepository.deleteById(id);
        return "Your Account has been deleted";
    }

    @Override
    public AccountDto updateAccount(Long id , AccountDto accountDto) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException(
                "This ID doesn't exit"));

        if(accountDto.getAccountHolderName()!=null){
            account.setAccountHolderName(accountDto.getAccountHolderName());
        }
        if(accountDto.getBalance()!=0){
            account.setBalance(accountDto.getBalance());
        }
        Account saved = accountRepository.save(account);
        return  AccountMapper.maptoAccountDto(saved);

    }


}
