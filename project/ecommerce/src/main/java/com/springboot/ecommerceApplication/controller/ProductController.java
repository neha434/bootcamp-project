package com.springboot.ecommerceApplication.controller;


import com.springboot.ecommerceApplication.co.ProductCO;
import com.springboot.ecommerceApplication.dto.ProductDto;
import com.springboot.ecommerceApplication.repositories.ProductRepo;
import com.springboot.ecommerceApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepo productRepository;
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Integer id){
        return productService.getProduct(id);

    }
    @GetMapping("/")
    public List<ProductDto> getProductList(){
        return productService.getProductList();
    }

    @PostMapping("/")
    public ProductDto addProduct(@RequestBody ProductCO productCO, WebRequest webRequest){
        return productService.addProduct(productCO);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Integer id,
                                    @RequestBody ProductCO productCO , WebRequest webRequest){
        return  productService.updateProduct(id,productCO);
    }

    @DeleteMapping("/{id}")
    public Map<String,Boolean> deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }
}
