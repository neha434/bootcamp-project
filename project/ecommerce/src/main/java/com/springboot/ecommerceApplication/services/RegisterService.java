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



    public ResponseEntity<String> registerAccount(String email) {
            ResponseEntity<String> responseEntity;
            User  user = userRepository.findByEmail(email);
            if (userRepository.findByEmail(email) == null){
                throw new AccountDoesNotExists("email address does not exists");
            }
//     else if(!user.isEnabled()){
//            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
//                    ("message-account-not-active", null, LocaleContextHolder.getLocale()));
//            return responseEntity;
//        }

                String token = UUID.randomUUID().toString();
                createVerificationToken(user, token); //This method will store token in table
                mailService.sendForgotPasswordLinkEmail(user.getEmail(),token);
                responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
                        ("message-account-activation-link", null, LocaleContextHolder.getLocale()));
                return responseEntity;
            }


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
//            user.setEnabled(true);
//            enableRegisteredUser(user);
//            return null;
     }

        public VerificationToken getVerificationToken(String token){
        return verificationTokenRepository.findByToken(token);
       }
        public void createVerificationToken(User user, String token){
        VerificationToken newToken = new VerificationToken(token,user);
        verificationTokenRepository.save(newToken);
      }
        public void enableRegisteredUser(User user) {
        userRepository.save(user);
       }

}
