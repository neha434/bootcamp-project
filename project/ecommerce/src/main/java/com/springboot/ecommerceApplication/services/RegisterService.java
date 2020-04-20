package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.ForgotPasswordToken;
import com.springboot.ecommerceApplication.domain.VerificationToken;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.PasswordDto;
import com.springboot.ecommerceApplication.dto.UserDto;
import com.springboot.ecommerceApplication.exception.AccountDoesNotExists;
import com.springboot.ecommerceApplication.repositories.UserRepo;
import com.springboot.ecommerceApplication.repositories.VerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class RegisterService {
    @Autowired
    VerificationTokenRepo verificationTokenRepository;
    @Autowired
    UserRepo userRepository;
    @Autowired
    MailService mailService;
    @Autowired
    MessageSource messageSource;

//activation of account

    public ResponseEntity<String> activateAccount(String token, UserDto userDto) {
            VerificationToken verificationToken = getVerificationToken(token);
            ResponseEntity<String> responseEntity;
            if (verificationToken == null){
                responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
                        ("message-invalid-verification-token", null, LocaleContextHolder.getLocale()));
                return responseEntity;
            }
            User user = verificationToken.getUser();
            Calendar calendar = Calendar.getInstance();
            //token is exired
          if (verificationToken.getExpiryDate().getTime() -calendar.getTime().getTime() <=0){
                verificationTokenRepository.delete(verificationToken);
                responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
                        ("message-verification-token-expired", null, LocaleContextHolder.getLocale()));
                return responseEntity;
            }
            verificationTokenRepository.delete(verificationToken);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                    ("message-account-verified", null, LocaleContextHolder.getLocale()));
            return responseEntity;

     }

        public VerificationToken getVerificationToken(String token){
        return verificationTokenRepository.findByToken(token);
       }


}
