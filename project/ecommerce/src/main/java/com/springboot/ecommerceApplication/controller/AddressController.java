package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.services.CustomerService;
import com.springboot.ecommerceApplication.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    SellerService sellerService;
    CustomerService customerService;

//update address
    @PutMapping("/Seller/{id}")
    public ResponseEntity<String> updateSellerAddress(@PathVariable Integer id, @Valid @RequestBody AddressDto addressDto , HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return  sellerService.updateSellerAddress(username,id,addressDto);
    }

    @PutMapping("/Customer{id}")
    public ResponseEntity<String> updateCustomerAddress(@PathVariable Integer id, @Valid @RequestBody AddressDto addressDto , HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return  customerService.updateCustomerAddress(username,id,addressDto);
    }
//view address
    @GetMapping("/view")
    public List<AddressDto> getAddress(HttpServletRequest httpServletRequest) {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.getAddress(username);
    }

    //delete address
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.deleteAddress(username, id);
    }
//add address
    @PostMapping("/Add")
    public String addAddress( @Valid @RequestBody AddressDto addressDto, HttpServletRequest httpServletRequest){
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.AddAddress(username,addressDto);
    }

}
