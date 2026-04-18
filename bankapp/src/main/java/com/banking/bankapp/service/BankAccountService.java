package com.banking.bankapp.service;

import com.banking.bankapp.model.BankAccount;
import com.banking.bankapp.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount findBankAccountByID(Long id){
        return bankAccountRepository.findById(id).orElse(null);
    }

    public BankAccount createBankAccount(BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Long id){bankAccountRepository.deleteById(id);}

    public BankAccount updateAccount(long id, BankAccount updatedAccount) {
        BankAccount existingAccount = bankAccountRepository.findById(id).orElse(null);

        if (existingAccount == null) {
            System.out.println("Account doesn't exist");
            return null;
        }

        existingAccount.setAccountHolderName(updatedAccount.getAccountHolderName());
        existingAccount.setBalance(updatedAccount.getBalance());
        return bankAccountRepository.save(existingAccount);


    }}
