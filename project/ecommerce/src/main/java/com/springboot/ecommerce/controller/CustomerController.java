package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.CustomerDto;
import com.springboot.ecommerce.dto.PasswordDto;
import com.springboot.ecommerce.repositories.CustomerRepo;
import com.springboot.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    CustomerService customerService;


    //..............................TO VIEW PROFILE........................
   @GetMapping("/viewProfile")
    public CustomerDto getCustomerProfile(HttpServletRequest httpServletRequest)
    {
      Principal principal=httpServletRequest.getUserPrincipal();
       String username=principal.getName();
        return customerService.getCustomer(username);
    }

   //..........................TO UPDATE PROFILE .........................
    @PutMapping("/update")
    public ResponseEntity<String> updateCustomerUsingPut( @Valid @RequestBody CustomerDto customerDto,HttpServletRequest httpServletRequest) {

        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
       return customerService.updateCustomer(username, customerDto, false);
    }
  @PatchMapping("/update")
    public ResponseEntity<String> updateCustomerUsingPatch( @RequestBody CustomerDto customerDto, HttpServletRequest httpServletRequest) {

      Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.updateCustomer(username, customerDto, true);
    }

    //......................TO UPDATE PASSWORD..........................
    @PutMapping("/changePassword")
    public ResponseEntity<String> changeCustomerPassword(@Valid @RequestBody PasswordDto passwordDto, HttpServletRequest httpServletRequest)
    {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.updatePassword(username, passwordDto);
    }




  }
