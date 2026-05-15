package com.banking.bankapp.service;

import com.banking.bankapp.exception.AccountNotFoundException;
import com.banking.bankapp.exception.InvalidAmountException;
import com.banking.bankapp.model.BankAccount;
import com.banking.bankapp.model.Transaction;
import com.banking.bankapp.repository.BankAccountRepository;
import com.banking.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount findBankAccountByID(Long id){
        return bankAccountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public BankAccount createBankAccount(BankAccount bankAccount){
        if(bankAccount.getBalance()<=0){
            throw new InvalidAmountException("Balance cannot be less than or equal to ZERO");
        }
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Long id){bankAccountRepository.deleteById(id);}

    public BankAccount updateAccount(long id, BankAccount updatedAccount) {
        BankAccount existingAccount = bankAccountRepository.findById(id).orElse(null);

        existingAccount.setAccountHolderName(updatedAccount.getAccountHolderName());
        existingAccount.setBalance(updatedAccount.getBalance());
        return bankAccountRepository.save(existingAccount);


    }

    public BankAccount deposit(Long id,Double amount){

        if(amount<=0){
            throw new InvalidAmountException("Amount cannot be zero or Less");
        }

        BankAccount account = findBankAccountByID(id);
        double amountPresent = account.getBalance()+amount;
        account.setBalance(amountPresent);
        bankAccountRepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("Deposit");
        transaction.setBankAccount(account);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        return account;
    }

    public BankAccount withDraw(Long id,Double amount){
        if(amount<=0){
            throw new InvalidAmountException("Amount cannot be zero or Less");
        }
        BankAccount account = findBankAccountByID(id);
        if(account.getBalance()<amount){
            throw new InvalidAmountException("Amount cannot be greater than balance");
        }
        Double existingBalance = account.getBalance();
        existingBalance = existingBalance-amount;
        account.setBalance(existingBalance);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType("Withdraw");
        transaction.setBankAccount(account);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        return account;

    }

    public List<Transaction> getTransactionsByAccountId(Long id) {
        BankAccount account = findBankAccountByID(id);
        return transactionRepository.findByBankAccount(account);
    }

    public void transferAmount(Long fromAccountId, Long toAccountId, double amount){
        BankAccount fromAccount = findBankAccountByID(fromAccountId);
        BankAccount toAccount = findBankAccountByID(toAccountId);
        if(fromAccount.getBalance() < amount){
            throw new InvalidAmountException("Amount cannot be less than balance");
        }

        double presentBalance = fromAccount.getBalance() - amount;
        fromAccount.setBalance(presentBalance);
        bankAccountRepository.save(fromAccount);

        Transaction fromTransaction = new Transaction();
        fromTransaction .setAmount(amount);
        fromTransaction .setTransactionType("Transfer TO");
        fromTransaction .setBankAccount(fromAccount);
        fromTransaction .setTimestamp(LocalDateTime.now());
        transactionRepository.save(fromTransaction);

        toAccount.setBalance(toAccount.getBalance() + amount);
        bankAccountRepository.save(toAccount);

        Transaction toTransaction= new Transaction();
        toTransaction.setAmount(amount);
        toTransaction.setTransactionType("Transfer FROM account ID: "+fromAccountId);
        toTransaction.setBankAccount(toAccount);
        toTransaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(toTransaction);

    }

    public List<BankAccount> findAccountByName(String name){
        return bankAccountRepository.findByAccountHolderNameContaining(name);
    }

}
