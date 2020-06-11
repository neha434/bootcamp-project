package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.co.CustomerCO;
import com.springboot.ecommerce.co.SellerCO;
import com.springboot.ecommerce.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;


@RestController
//@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    RegisterService registerService;
//Register a customer
    @PostMapping("/customerRegister")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody CustomerCO customerCO, WebRequest webRequest){
        return registerService.registerCustomer(customerCO);
    }

    //to activate customer account
    @PutMapping("/activate-user-account")
    public ResponseEntity<String> activateAccount(@RequestParam("token") String token
            ,WebRequest webRequest){
        return registerService.activateAccount(token);
    }

//register a seller
    @PostMapping("/sellerRegister")
    public ResponseEntity<String> registerSeller(@Valid @RequestBody SellerCO sellerCO, WebRequest webRequest){
        return registerService.registerSeller(sellerCO);
    }


   }