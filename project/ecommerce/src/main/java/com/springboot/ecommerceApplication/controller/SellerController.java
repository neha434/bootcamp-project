package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.co.SellerCO;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.SellerDto;
import com.springboot.ecommerceApplication.repositories.SellerRepo;
import com.springboot.ecommerceApplication.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/seller")
public class SellerController {


    @Autowired
    SellerRepo sellerRepository;
    @Autowired
    SellerService sellerService;
    @GetMapping("/{id}")
    public SellerDto getSeller(@PathVariable Integer id){
        return  sellerService.getSeller(id);
    }

//    @PutMapping("/address/{id}")
//    public String updateAddress(@PathVariable Integer id, @Valid @RequestBody AddressDto addressDto , WebRequest webRequest){
//        return  sellerService.updateAddress(id,addressDto);
//    }


    @PutMapping("/{id}")
    public SellerDto updateSeller(@PathVariable Integer id,@Valid @RequestBody SellerCO sellerCO , WebRequest webRequest){
        return  sellerService.updateSeller(id,sellerCO);
    }

    @DeleteMapping("/{id}")
    public Map<String,Boolean> deleteSeller(@PathVariable Integer id){
        return sellerService.deleteSeller(id);
    }


}