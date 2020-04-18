package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.dto.CustomerDto;
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

    @GetMapping("/customer")
    public List<CustomerDto> getAllCustomer(@RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @RequestParam(defaultValue = "id") Integer sortBy)
    {
        //List<CustomerDto> list = customerService.getAllCustomer(pageNo, pageSize, sortBy);
        //return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);

        return  customerService.getAllCustomer(pageNo, pageSize, sortBy);
    }

    @GetMapping("/seller")
    public List<SellerDto> getAllSeller(@RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "id") Integer sortBy)
{
        return sellerService.getAllSeller(pageNo, pageSize, sortBy);
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