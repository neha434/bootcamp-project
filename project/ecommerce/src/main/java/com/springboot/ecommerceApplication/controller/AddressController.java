package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.services.CustomerService;
import com.springboot.ecommerceApplication.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    SellerService sellerService;
    CustomerService customerService;

//update address
    @PutMapping("/Seller/{id}")
    public ResponseEntity<String> updateSellerAddress(@PathVariable Integer id, @Valid @RequestBody AddressDto addressDto , WebRequest webRequest){
        return  sellerService.updateAddress(id,addressDto);
    }
    @PutMapping("/Customer{id}")
    public ResponseEntity<String> updateCustomerAddress(@PathVariable Integer id, @Valid @RequestBody AddressDto addressDto , WebRequest webRequest){
        return  customerService.updateCustomerAddress(id,addressDto);
    }

    @GetMapping("/{id}")
    public AddressDto getAddress(@PathVariable Integer id) {
        return customerService.getAddress(id);
    }
//delete address
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteAddress(@PathVariable Integer id) {
        return customerService.deleteAddress(id);
    }
//add address
    @PostMapping("/Add")
    public String addAddress(Integer id, @Valid @RequestBody AddressDto addressDto, WebRequest webRequest){
        return customerService.AddAddress(id,addressDto);
    }


}
