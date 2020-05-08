package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.PasswordDto;
import com.springboot.ecommerceApplication.repositories.CustomerRepo;
import com.springboot.ecommerceApplication.services.CustomerService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<String> changeCustomerPassword(@RequestBody PasswordDto passwordDto, HttpServletRequest httpServletRequest)
    {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.updatePassword(username, passwordDto);
    }




  }
