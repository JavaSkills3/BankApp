package com.banking.bankapp.controller;

import com.banking.bankapp.model.BankAccount;
import com.banking.bankapp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccount> getAllAccounts(){
        return bankAccountService.getAllBankAccounts();
    }
    @GetMapping("/{id}")
    public BankAccount getAccountById(@PathVariable Long id){
        return bankAccountService.findBankAccountByID(id);
    }
    @PostMapping()
    public BankAccount saveAccount(@RequestBody BankAccount bankAccount){
        return bankAccountService.createBankAccount(bankAccount);
    }
    @DeleteMapping("/{id}")
    public String deleteAccountById(@PathVariable Long id){
         bankAccountService.deleteBankAccount(id);
         return "Deleted account with id: "+id;
    }
}
