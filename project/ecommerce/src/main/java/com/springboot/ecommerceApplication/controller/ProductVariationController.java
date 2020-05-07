package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.services.ProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController

public class ProductVariationController {
    @Autowired
    ProductVariationService productVariationService;

    @PostMapping("/add-product-variation")
    public ResponseEntity<String>  addProductVariation(HttpServletRequest httpServletRequest, Integer productId,
                                                       Integer price, Integer quantity)
    {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return  productVariationService.addProductVariation(username, productId, price, quantity );

    }
}
