package com.banking.bankapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Bank_Account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_seq")
    @SequenceGenerator(name = "bank_seq", sequenceName = "bank_seq", allocationSize = 1)
    private Long id;
    private String accountHolderName;
    private Double balance;
    private AccountType accountType;

    public Long getId(){
        return id;
    }

    public String getAccountHolderName(){
        return accountHolderName;
    }

    public Double getBalance(){
        return balance;
    }

    public void setAccountHolderName(String accountHolderName){
        this.accountHolderName = accountHolderName;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public BankAccount(){

    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public AccountType getAccountType() {
        return accountType;
    }
}
