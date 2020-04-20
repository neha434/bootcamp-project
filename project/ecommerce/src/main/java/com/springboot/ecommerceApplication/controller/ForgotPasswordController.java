package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.dto.PasswordDto;
import com.springboot.ecommerceApplication.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
public class ForgotPasswordController {
    @Autowired
    UserLoginService userLoginService;

    @PostMapping(value="/forgotPassword")
    public ResponseEntity<String> getResetPasswordToken(@RequestParam(value="email") String email, WebRequest request){
        return userLoginService.forgotPasswordToken(email);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @Valid @RequestBody PasswordDto forgotPasswordDto
            ,WebRequest webRequest){
        return userLoginService.resetPassword(token,forgotPasswordDto);
    }


}
