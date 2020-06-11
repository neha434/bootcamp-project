package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.PasswordDto;
import com.springboot.ecommerce.services.UserLoginService;
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
