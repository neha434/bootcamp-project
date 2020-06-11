package com.springboot.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidDetails extends RuntimeException {
    public InvalidDetails(String message){
        super(message);
    }
}