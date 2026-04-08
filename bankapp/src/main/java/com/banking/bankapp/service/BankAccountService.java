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

    public void deleteBankAccount(Long id){
        bankAccountRepository.deleteById(id);
    }
}
