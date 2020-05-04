package com.springboot.ecommerceApplication.controller;


import com.springboot.ecommerceApplication.dto.*;
import com.springboot.ecommerceApplication.enums.UserRole;
import com.springboot.ecommerceApplication.services.CustomerService;
import com.springboot.ecommerceApplication.services.ProductService;
import com.springboot.ecommerceApplication.services.SellerService;
import com.springboot.ecommerceApplication.services.UserService;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController

public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;
    @Autowired
    ProductService productService;

    //get list of customer
//    @GetMapping("/customer/")
//    public List<CustomerDto> getAllCustomer(PagingAndSortingDto pagingAndSortingDto)
//    {
//        return  customerService.getAllCustomer(pagingAndSortingDto);
//    }
    @GetMapping("/customer/")
    public List<CustomerDto> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    //get list of sellers
//    @GetMapping("/seller/")
//    public List<SellerDto> getAllSeller(@PageableDefault() Pageable pageable)
//{
//        return sellerService.getAllSeller(pageable);
//    }
    @GetMapping("/seller/")
    public List<SellerDto> getAllSeller() {
        return sellerService.getAllSeller();
    }


    @PutMapping("/changeRole/{role}/{id}")
    public String changeRoleOfUser(@PathVariable Integer id, UserRole role) {
        return userService.changeRoleOfUser(id, role);
    }

    //to activate a user
    @PutMapping("/activate/{id}")
    public ResponseEntity activateUser(@PathVariable Integer id, WebRequest webRequest) {
        return userService.activateDeactivateById(id, true);
    }

    //to deactivate a user
    @PutMapping("/deactivate/{id}")
    public ResponseEntity deActivateUser(@PathVariable Integer id, WebRequest webRequest) {
        return userService.activateDeactivateById(id, false);
    }

    //to get product list
    @GetMapping("/product/")
    public List<ProductDto> getProductList() {
        return productService.getProductList();
    }

    //to update profile
    @PatchMapping("/updateAdmin/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto, WebRequest webRequest) {
        return userService.updateUser(id, userDto);
    }

    //to activate a product
    @PutMapping("/activateProduct/{id}")
    public ResponseEntity activateProduct(@PathVariable Integer id, WebRequest webRequest) {
        return userService.activateDeactivateProductById(id, true);
    }

    //to deactivate a product
    @PutMapping("/deactivateProduct/{id}")
    public ResponseEntity deActivateProduct(@PathVariable Integer id, WebRequest webRequest) {
        return userService.activateDeactivateProductById(id, false);
    }

}