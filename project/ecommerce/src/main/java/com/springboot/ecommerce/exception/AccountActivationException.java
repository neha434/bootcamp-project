package com.springboot.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountActivationException extends RuntimeException {
    public AccountActivationException(String message){
        super(message);
    }
}