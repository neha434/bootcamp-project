package com.springboot.ecommerce.controller;


import com.springboot.ecommerce.dto.*;
import com.springboot.ecommerce.enums.UserRole;
import com.springboot.ecommerce.services.CustomerService;
import com.springboot.ecommerce.services.ProductService;
import com.springboot.ecommerce.services.SellerService;
import com.springboot.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    public List<CustomerDto> getAllCustomer(HttpServletRequest httpServletRequest)
    {    Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return customerService.getAllCustomer(username);
    }

    //get list of sellers
//    @GetMapping("/seller/")
//    public List<SellerDto> getAllSeller(@PageableDefault() Pageable pageable)
//{
//        return sellerService.getAllSeller(pageable);
//    }
    @GetMapping("/seller/")
    public List<SellerDto> getAllSeller(HttpServletRequest httpServletRequest) {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return sellerService.getAllSeller(username);
    }


    @PutMapping("/changeRole/{role}/{id}")
    public String changeRoleOfUser(@PathVariable Integer id, UserRole role, HttpServletRequest httpServletRequest) {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return userService.changeRoleOfUser(username,id, role);
    }

    //to activate a user
    @PutMapping("/activate/{id}")
    public ResponseEntity activateUser(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return userService.activateDeactivateById(username,id, true);
    }

    //to deactivate a user
    @PutMapping("/deactivate/{id}")
    public ResponseEntity deActivateUser(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();

        return userService.activateDeactivateById(username,id, false);
    }

    //to get product list
    @GetMapping("/product/")
    public List<ProductDto> getProductList(HttpServletRequest httpServletRequest)
    {  Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();

        return productService.getProductList(username);
    }

    //to update profile
    @PatchMapping("/updateAdmin")
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto, HttpServletRequest httpServletRequest) {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();

        return userService.updateUser(username, userDto);
    }

    //to activate a product
    @PutMapping("/activateProduct/{id}")
    public ResponseEntity activateProduct(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();

        return userService.activateDeactivateProductById(username,id, true);
    }

    //to deactivate a product
    @PutMapping("/deactivateProduct/{id}")
    public ResponseEntity deActivateProduct(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();

        return userService.activateDeactivateProductById(username,id, false);
    }

    @GetMapping("/viewProduct/{productId}")
    public ProductDto getCategory(@PathVariable Integer productId, HttpServletRequest httpServletRequest)
    {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return productService.getProductByAdmin(username,productId);
    }


}