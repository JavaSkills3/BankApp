package com.banking.bankapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFound(AccountNotFoundException ex){

        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }
    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<?> invalidAmount(InvalidAmountException ex){
        ErrorResponse error = new ErrorResponse(400,ex.getMessage());
        return ResponseEntity.status(400).body(error);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex){
    ErrorResponse error = new ErrorResponse(400,"Invalid request body");
    return ResponseEntity.status(400).body(error);
    }
}
