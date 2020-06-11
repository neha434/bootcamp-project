package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.ProductVariationDto;
import com.springboot.ecommerce.services.ProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController

public class ProductVariationController {
    @Autowired
    ProductVariationService productVariationService;

    //.............TO ADD PRODUCT VARIATION BY SELLER........................
    @PostMapping("/add-product-variation/{productId}")
    public ResponseEntity<String> addProductVariation(HttpServletRequest httpServletRequest, @PathVariable Integer productId,
                                                      @Valid @RequestBody ProductVariationDto productVariationDto) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.addProductVariation(username, productId, productVariationDto );

    }

    //...........TO UPDATE PRODUCT VARIATION BY SELLER.................
    @PutMapping("/update-productVariation/{productVariationId}")
    public ResponseEntity<String> updateProductVariation(@PathVariable Integer productVariationId,
                                                         @Valid @RequestBody ProductVariationDto productVariationDto,
                                                         HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.updateProductVariationBySeller(username, productVariationId, productVariationDto);
    }

    //................TO VIEW A PRODUCT VARIATION BY SELLER..................
    @GetMapping("/get-productVariation/{productVariationId}")
    public ProductVariationDto getProductVariation(@PathVariable Integer productVariationId, HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.getProductVariationBySeller(productVariationId, username);

    }

    //..................TO GET LIST OF PRODUCT VARIATION BY SELLER.................................
    @GetMapping("/get-productVariation-list/{productId}")
    public List<ProductVariationDto> getProductList(@PathVariable Integer productId, HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productVariationService.getProductVariationListBySeller(username,productId);

    }



}
