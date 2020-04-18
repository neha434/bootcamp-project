package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.PagingAndSortingDto;
import com.springboot.ecommerceApplication.dto.SellerDto;
import com.springboot.ecommerceApplication.services.CustomerService;
import com.springboot.ecommerceApplication.services.SellerService;
import com.springboot.ecommerceApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;
    @Autowired
    SellerService sellerService;
   //get list of customer
    @GetMapping("/customer")
    public List<CustomerDto> getAllCustomer(PagingAndSortingDto pagingAndSortingDto)
    {
        //List<CustomerDto> list = customerService.getAllCustomer(pageNo, pageSize, sortBy);
        //return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);

        return  customerService.getAllCustomer(pagingAndSortingDto);
    }
 //get list of sellers
    @GetMapping("/seller")
    public List<SellerDto> getAllSeller(PagingAndSortingDto pagingAndSortingDto)
{
        return sellerService.getAllSeller(pagingAndSortingDto);
    }

    @PutMapping("/changeRole/{role}/{id}")
        public String changeRoleOfUser(@PathVariable Integer id, String role){
        return userService.changeRoleOfUser(id,role);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity activateUser(@PathVariable Integer id, WebRequest webRequest) {
        return userService.activateById(id, webRequest);
    }

    @PutMapping("/deactivate{id}")
    public ResponseEntity deActivateUser(@PathVariable Integer id, WebRequest webRequest) {
        return userService.deActivateById(id, webRequest);
    }

}