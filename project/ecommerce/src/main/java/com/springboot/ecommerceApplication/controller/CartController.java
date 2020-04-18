package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.dto.CartDto;
import com.springboot.ecommerceApplication.dto.ProductDto;
import com.springboot.ecommerceApplication.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController

@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/")
    public ProductDto addProductToCart(@RequestBody CartDto cartDto, WebRequest webrequest){
            return cartService.addProductToCart(cartDto);
    }

    @GetMapping("/")
    public List<CartDto> getCartProduct(){
        return cartService.getCartProduct();
    }
}

