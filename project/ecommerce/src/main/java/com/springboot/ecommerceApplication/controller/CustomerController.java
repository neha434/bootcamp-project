package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.repositories.CustomerRepo;
import com.springboot.ecommerceApplication.services.CustomerService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    CustomerService customerService;
   //view profile
    @GetMapping("/id")
    public CustomerDto getCustomer(Integer id)
    {
        return customerService.getCustomer(id);
    }
  //update profile
    @PutMapping("/{id}")
    public CustomerDto updateCustomerUsingPut(@PathVariable Integer id, @Valid @RequestBody CustomerDto customerDto,  WebRequest webRequest) {
        return customerService.updateCustomer(id, customerDto, false);
    }
    @PatchMapping("/{id}")
    public CustomerDto updateCustomerUsingPatch(@PathVariable Integer id, @RequestBody CustomerDto customerDto, WebRequest webRequest) {
        return customerService.updateCustomer(id, customerDto, true);
    }
//    @PatchMapping("/patch/{id}")
//   public ResponseEntity<String> updateUser( @PathVariable Integer id, @RequestBody CustomerCO customerCO, WebRequest webRequest ) {
//        return customerService.updateCustomer(id, customerCO);
//    }


}