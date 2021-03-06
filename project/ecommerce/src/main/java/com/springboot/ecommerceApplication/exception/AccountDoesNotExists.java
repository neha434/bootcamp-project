package com.springboot.ecommerceApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountDoesNotExists extends RuntimeException {
    public AccountDoesNotExists(String message){
        super(message);
    }
}