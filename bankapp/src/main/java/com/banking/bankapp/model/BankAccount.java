package com.banking.bankapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Bank_Account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String accountHolderName;
    private Double balance;

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
}
