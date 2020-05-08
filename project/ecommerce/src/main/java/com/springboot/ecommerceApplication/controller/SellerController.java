package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.co.SellerCO;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.PasswordDto;
import com.springboot.ecommerceApplication.dto.SellerDto;
import com.springboot.ecommerceApplication.repositories.SellerRepo;
import com.springboot.ecommerceApplication.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/seller")
public class SellerController {


    @Autowired
    SellerRepo sellerRepository;
    @Autowired
    SellerService sellerService;



  //...........TO VIEW PROFILE.....................
  @GetMapping("/viewProfile")
  public SellerDto getSellerProfile(HttpServletRequest httpServletRequest)
  {
      Principal principal=httpServletRequest.getUserPrincipal();
      String username=principal.getName();
      return sellerService.getSeller(username);
  }

  //............TO UPDATE PROFILE............................
    @PutMapping("/update")
    public ResponseEntity<String> updateSellerUsingPut( @Valid @RequestBody SellerDto sellerDto,HttpServletRequest httpServletRequest) {
      Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return sellerService.updateCustomer(username, sellerDto, false);
    }
    @PatchMapping("/update")
    public ResponseEntity<String> updateSellerUsingPatch( @RequestBody SellerDto sellerDto, HttpServletRequest httpServletRequest) {

        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return sellerService.updateCustomer(username, sellerDto, true);
    }
    //......................TO UPDATE PASSWORD..........................
    @PutMapping("/changePassword")
    public ResponseEntity<String> changeCustomerPassword(@RequestBody PasswordDto passwordDto, HttpServletRequest httpServletRequest)
    {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return sellerService.updatePassword(username, passwordDto);
    }


}