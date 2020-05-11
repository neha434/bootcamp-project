package com.springboot.ecommerceApplication.controller;


import com.springboot.ecommerceApplication.co.ProductCO;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.ProductDto;
import com.springboot.ecommerceApplication.repositories.ProductRepo;
import com.springboot.ecommerceApplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepo productRepository;
    @Autowired
    ProductService productService;

    //.........TO GET A PRODUCT BY SELLER.............
    @GetMapping("/get-product/{productId}")
    public ProductDto getProduct(@PathVariable Integer productId, HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productService.getProduct(productId, username);

    }

    //............TO ADD A PRODUCT BY SELLER......................edit
    @PostMapping("/add-product")
    public ResponseEntity<String> addProductVariation(HttpServletRequest httpServletRequest, @RequestBody ProductDto productDto) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productService.addProduct(username, productDto);

    }

    //..........TO GET PRODUCT LIST BY SELLER...................
    @GetMapping("/get-product-list")
    public List<ProductDto> getProductList(HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productService.getProductListBySeller(username);

    }

    //..................TO UPDATE A PRODUCT BY SELLER............................
    @PutMapping("/update-product/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer productId, @Valid @RequestBody ProductDto productDto, HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productService.updateProductBySeller(username, productId, productDto);
    }

    //.....................TO DELETE A PRODUCT BY SELLER.............
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer productId, HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productService.deleteProductBySeller(productId, username);
    }


}



