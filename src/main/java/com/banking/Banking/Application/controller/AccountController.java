package com.banking.Banking.Application.controller;

import com.banking.Banking.Application.dto.AccountDto;
import com.banking.Banking.Application.services.AccountServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {
    private AccountServices accountServices;

    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }
   @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody  AccountDto accountDto){
        return new ResponseEntity<>(accountServices.createAccount(accountDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountId( @PathVariable  Long id){
        return new ResponseEntity<>(accountServices.getAccountById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAccount(@PathVariable Long id , @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountServices.depositAccount(id,amount);
        return  ResponseEntity.ok(accountDto);


    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawAccount(@PathVariable Long id ,@RequestBody Map<String,Double>request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountServices.withdrawAccount(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        return new ResponseEntity<>(accountServices.getAllAccount(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.ok(accountServices.deleteAccount(id));

    }
     @PutMapping("/{id}")
    public  ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto accountDto){

        return ResponseEntity.ok(accountServices.updateAccount(id,accountDto));
    }
}