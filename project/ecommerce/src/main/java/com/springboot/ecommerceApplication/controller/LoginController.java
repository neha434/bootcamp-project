package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login-user")
    public void login(@RequestParam(name = "email")  String email, @RequestParam(name = "password")
            String password, @RequestParam(name= "clientId") String clientId,
                      @RequestParam(name = "clientSecret" ) String clientSecret){
        loginService.login(email,password,clientId,clientSecret);



    }
}