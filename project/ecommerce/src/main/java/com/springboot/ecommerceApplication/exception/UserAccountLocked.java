package com.springboot.ecommerceApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class UserAccountLocked extends RuntimeException {
    public UserAccountLocked(String message){
        super(message);
    }
}