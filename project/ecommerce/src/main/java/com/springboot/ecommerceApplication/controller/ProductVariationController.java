package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.dto.ProductVariationDto;
import com.springboot.ecommerceApplication.services.ProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController

public class ProductVariationController {
    @Autowired
    ProductVariationService productVariationService;

    @PostMapping("/add-product-variation/{productId}")
    public ResponseEntity<String> addProductVariation(HttpServletRequest httpServletRequest, @PathVariable Integer productId,
                                                      @Valid @RequestBody ProductVariationDto productVariationDto) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.addProductVariation(username, productId, productVariationDto );

    }

    @PutMapping("/update-productVariation/{productVariationId}")
    public ResponseEntity<String> updateProductVariation(@PathVariable Integer productVariationId,
                                                         @Valid @RequestBody ProductVariationDto productVariationDto,
                                                         HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.updateProductVariationBySeller(username, productVariationId, productVariationDto);
    }
}
