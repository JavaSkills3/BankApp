package com.banking.bankapp.repository;

import com.banking.bankapp.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{


    public List<BankAccount> findByAccountHolderNameContaining(String name);
}
