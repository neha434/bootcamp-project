package com.springboot.ecommerceApplication.controller;


import com.springboot.ecommerceApplication.dto.PasswordDto;
import com.springboot.ecommerceApplication.dto.UserDto;
import com.springboot.ecommerceApplication.services.RegisterService;
import com.springboot.ecommerceApplication.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class ActivationController {
    @Autowired
    RegisterService registerService;

    @PostMapping("/register-account")
    public ResponseEntity<String> getRegisterAccountToken(@RequestParam("email") String email, WebRequest request){
        return registerService.registerAccount(email);
    }

    @PutMapping("/activate-account")
    public ResponseEntity<String> activateAccount(@RequestParam("token") String token, @RequestBody UserDto userDto
            ,WebRequest webRequest){
        return registerService.activateAccount(token,userDto);
    }
}
