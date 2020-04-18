package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.domain.GenericResponse;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.PasswordDto;
import com.springboot.ecommerceApplication.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class LoginController {
    @Autowired
    UserLoginService userLoginService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> getResetPasswordToken(@RequestParam("email") String email, WebRequest request){
        return userLoginService.forgotPasswordToken(email);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @Valid @RequestBody PasswordDto forgotPasswordDto
            ,WebRequest webRequest){
        return userLoginService.resetPassword(token,forgotPasswordDto);
    }

//    @GetMapping("/changePassword")
//    public String showChangePasswordPage(Locale locale, Model model,
//                                         @RequestParam("id") int id, @RequestParam("token") String token) {
//        String result = userLoginService.validatePasswordResetToken(id, token);
//        if (result != null) {
//            model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
//            return "redirect:/login?lang=" + locale.getLanguage();
//        }
//
//    }
//      //  return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
//
//    @PostMapping("/savePassword")
//      public GenericResponse savePassword(Locale locale, @Valid PasswordDto passwordDto) {
//          User user =
//                  (User) SecurityContextHolder.getContext()
//                          .getAuthentication().getPrincipal();
//
//          userLoginService.changeUserPassword(user, passwordDto.getNewPassword());
//          return new GenericResponse( messages.getMessage("message.resetPasswordSuc", null, locale));
//      }
}
