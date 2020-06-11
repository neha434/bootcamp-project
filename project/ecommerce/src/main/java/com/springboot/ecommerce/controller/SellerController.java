package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.PasswordDto;
import com.springboot.ecommerce.dto.SellerDto;
import com.springboot.ecommerce.repositories.SellerRepo;
import com.springboot.ecommerce.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

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