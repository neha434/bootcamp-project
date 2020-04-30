package com.springboot.ecommerceApplication.controller;


import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.PagingAndSortingDto;
import com.springboot.ecommerceApplication.dto.SellerDto;
import com.springboot.ecommerceApplication.enums.UserRole;
import com.springboot.ecommerceApplication.services.CustomerService;
import com.springboot.ecommerceApplication.services.SellerService;
import com.springboot.ecommerceApplication.services.UserService;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
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
   //get list of customer
//    @GetMapping("/customer/")
//    public List<CustomerDto> getAllCustomer(PagingAndSortingDto pagingAndSortingDto)
//    {
//        return  customerService.getAllCustomer(pagingAndSortingDto);
//    }
    @GetMapping("/customer/")
    public List<CustomerDto> getAllCustomer()
    {
        return  customerService.getAllCustomer();
    }
 //get list of sellers
//    @GetMapping("/seller/")
//    public List<SellerDto> getAllSeller(@PageableDefault() Pageable pageable)
//{
//        return sellerService.getAllSeller(pageable);
//    }
    @GetMapping("/seller/")
    public List<SellerDto> getAllSeller()
    {
        return sellerService.getAllSeller();
    }


    @PutMapping("/changeRole/{role}/{id}")
        public String changeRoleOfUser(@PathVariable Integer id, UserRole role){
        return userService.changeRoleOfUser(id,role);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity activateUser(@PathVariable Integer id, WebRequest webRequest) {
        return userService.activateDeactivateById(id,true);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity deActivateUser(@PathVariable Integer id, WebRequest webRequest) {
        return userService.activateDeactivateById(id, false);
    }

}