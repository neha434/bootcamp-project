package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.co.SellerCO;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.SellerDto;
import com.springboot.ecommerceApplication.exception.CustomerAlreadyExistsException;
import com.springboot.ecommerceApplication.repositories.CustomerRepo;
import com.springboot.ecommerceApplication.services.CustomerService;
import com.springboot.ecommerceApplication.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;


@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    CustomerService customerService;
    SellerService sellerService;

    @PostMapping("customer/")
    public CustomerDto registerCustomer(@Valid @RequestBody CustomerCO customerCO, WebRequest webRequest){
        return customerService.registerCustomer(customerCO);
    }
    @PostMapping("seller/")
    public SellerDto registerSeller(@Valid @RequestBody SellerCO sellerCO, WebRequest webRequest){
        return sellerService.registerSeller(sellerCO);
    }



}