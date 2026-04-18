package com.banking.bankapp.controller;

import com.banking.bankapp.model.BankAccount;
import com.banking.bankapp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAccountById(@PathVariable Long id){
        BankAccount account =  bankAccountService.findBankAccountByID(id);
        if(account == null){
            return ResponseEntity.status(404).body("No account found");
        }
        else return ResponseEntity.status(200).body(account);
    }
    @PostMapping()
    public ResponseEntity<?> saveAccount(@RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountService.createBankAccount(bankAccount);
        if(account != null){
            System.out.println("Account created");
            return ResponseEntity.status(201).body(account);}
        return ResponseEntity.status(404).body("Something went wrong");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable Long id){
        BankAccount account = bankAccountService.findBankAccountByID(id);
        if(account != null){
            bankAccountService.deleteBankAccount(id);
            return ResponseEntity.status(200).body("Account deleted with ID: "+id+" successfully");
        }
        return ResponseEntity.status(404).body("Account doesn't exist");
    }

    @GetMapping("/test")
    public String test(){
        return "Test is working";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable long id,@RequestBody BankAccount account){
        BankAccount existingAccount = bankAccountService.findBankAccountByID(id);
        if(existingAccount == null){
            return ResponseEntity.status(404).body("no account exists with Id: "+id);
        }
        bankAccountService.updateAccount(id,account);
        return ResponseEntity.status(200).body("Account with Id "+id+" is updated");

    }
}
