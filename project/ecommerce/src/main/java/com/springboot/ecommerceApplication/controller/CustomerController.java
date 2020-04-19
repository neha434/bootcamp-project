package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.repositories.AddressRepository;
import com.springboot.ecommerceApplication.repositories.CustomerRepo;
import com.springboot.ecommerceApplication.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    CustomerService customerService;
   //view profile
    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);
    }
  //update profile
    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Integer id, @Valid @RequestBody CustomerCO customerCO, WebRequest webRequest) {
        return customerService.updateCustomer(id, customerCO);
    }
//
//    @DeleteMapping("/{id}")
//    public Map<String, Boolean> deleteUser(@PathVariable Integer id) {
//        return customerService.deleteCustomer(id);
//    }

}