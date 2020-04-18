package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.ForgotPasswordToken;
//import com.springboot.ecommerceApplication.domain.VerificationToken;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.PasswordDto;
import com.springboot.ecommerceApplication.exception.AccountDoesNotExists;
import com.springboot.ecommerceApplication.exception.InvalidDetails;
import com.springboot.ecommerceApplication.repositories.ForgotPasswordTokenRepo;
import com.springboot.ecommerceApplication.repositories.UserRepo;
//import com.springboot.ecommerceApplication.repositories.VerificationTokenRepo;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserLoginService {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    MailService mailService;
    @Autowired
    MessageSource messageSource;
    @Autowired
    UserRepo userRepository;
    @Autowired
    ForgotPasswordTokenRepo forgotPasswordTokenRepository;

//token genrator
    public ResponseEntity<String> forgotPasswordToken(String email){
        ResponseEntity<String> responseEntity;
        if(!isValidEmail(email)) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
                    ("message-invalid-email", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }
        User  user = userRepository.findByEmail(email);
        if (userRepository.findByEmail(email) == null){
            throw new AccountDoesNotExists("email address does not exist in database");
        }

//        else if(!user.isEnabled()){
//            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
//                    ("message-account-not-active", null, LocaleContextHolder.getLocale()));
//            return responseEntity;
//        }
        else{
//to create a token
            String token = UUID.randomUUID().toString();
            createForgotPasswordToken(user, token); //This method will store token in table

            mailService.sendForgotPasswordLinkEmail(user.getEmail(),token);
        }
        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
                ("message-password-reset-link", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    public void createForgotPasswordToken(User user, String token) {
    ForgotPasswordToken myToken = new ForgotPasswordToken(token, user);
    forgotPasswordTokenRepository.save(myToken);
    }

    public ResponseEntity<String> resetPassword(String token, PasswordDto forgotPasswordDto) {
        ForgotPasswordToken forgotPasswordToken = getForgotPasswordToken(token);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ResponseEntity<String> responseEntity;

        if (forgotPasswordToken == null){
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
                    ("message-invalid-forgot-password-token", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }
        passwordMatches(forgotPasswordDto.getPassword(),forgotPasswordDto.getConfirmPassword());

        User user = forgotPasswordToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (forgotPasswordToken.getExpiryDate().getTime() -calendar.getTime().getTime() <=0){
            forgotPasswordTokenRepository.delete(forgotPasswordToken);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
                    ("message-forgot-password-token-expired", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }
        user.setPassword(passwordEncoder.encode(forgotPasswordDto.getPassword()));
        userRepository.save(user);
        forgotPasswordTokenRepository.delete(forgotPasswordToken);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-password-changed", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }


    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }


    public boolean isValidEmail(String email){
        final String EMAIL_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*])(?=.{8,15})";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public ForgotPasswordToken getForgotPasswordToken(String token){
        return forgotPasswordTokenRepository.findByToken(token);
    }

    public boolean passwordMatches(String str1, String str2){
        if (!str1.equals(str2)){
            throw new InvalidDetails("Password And Confirm Password Are No Same");
        }
        return true;
    }
}