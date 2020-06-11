package com.springboot.ecommerce.controller;


import com.springboot.ecommerce.dto.ProductDto;
import com.springboot.ecommerce.repositories.ProductRepo;
import com.springboot.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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


    //................................TO VIEW A PRODUCT BY CUSTOMER......................
    @GetMapping("/view-myProduct/{productId}")
    public ProductDto viewProduct(@PathVariable Integer productId, HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productService.getProductByCustomer(username, productId);
    }

    //................TO GET PRODUCT LIST BY CUSTOMER...........................
    @GetMapping("/get-product-list/{categoryId}")
    public List<ProductDto> getProductListByCustomer(@PathVariable Integer categoryId, HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return productService.getProductListByCustomer(username, categoryId);

    }


}



