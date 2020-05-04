package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.co.SellerCO;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.SellerDto;
import com.springboot.ecommerceApplication.dto.UserDto;
import com.springboot.ecommerceApplication.exception.CustomerAlreadyExistsException;
import com.springboot.ecommerceApplication.repositories.CustomerRepo;
import com.springboot.ecommerceApplication.services.CustomerService;
import com.springboot.ecommerceApplication.services.RegisterService;
import com.springboot.ecommerceApplication.services.SellerService;
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