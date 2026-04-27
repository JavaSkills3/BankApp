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
         return ResponseEntity.status(200).body(account);
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
    public ResponseEntity<?> deleteBankAccount(@PathVariable Long id){
        bankAccountService.findBankAccountByID(id);
        bankAccountService.deleteBankAccount(id);

            return ResponseEntity.status(200).body("Account deleted with ID: "+id+" successfully");


    }

    @GetMapping("/test")
    public String test(){
        return "Test is working";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable long id,@RequestBody BankAccount account){
        bankAccountService.findBankAccountByID(id);
        bankAccountService.updateAccount(id,account);
        return ResponseEntity.status(200).body("Account with Id "+id+" is updated");

    }
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<?> withDrawAmount(@PathVariable long id,@RequestBody Double amount){
          BankAccount account = bankAccountService.withDraw(id,amount);
          return ResponseEntity.status(200).body(account);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<?> deposit(@PathVariable long id,@RequestBody Double amount){
        BankAccount account = bankAccountService.deposit(id,amount);
        return ResponseEntity.status(200).body(account);
    }

}
