package com.banking.bankapp.repository;

import com.banking.bankapp.model.BankAccount;
import com.banking.bankapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findByBankAccount(BankAccount bankAccount);
}
