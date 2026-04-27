package com.banking.bankapp.exception;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(String message){
    super(message);}
}
