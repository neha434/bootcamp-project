package com.springboot.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyExistException extends RuntimeException {
        public ProductAlreadyExistException(String message){
            super(message);
        }
    }
