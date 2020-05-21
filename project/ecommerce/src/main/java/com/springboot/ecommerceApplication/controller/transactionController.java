package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.services.TransactionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class transactionController {

    @Autowired
    TransactionUserService transactionUserService;

    @GetMapping("/test")
    public void checktransaction()
    {
         transactionUserService.parentMethod();
    }


}
