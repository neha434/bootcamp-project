package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.CartDto;
import com.springboot.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
//    @PostMapping("/")
//    public ProductDto addProductToCart(@RequestBody CartDto cartDto, WebRequest webrequest){
//            return cartService.addProductToCart(cartDto);
//    }

    @GetMapping("/")
    public List<CartDto> getCartProduct(){
        return cartService.getCartProduct();
    }
}

