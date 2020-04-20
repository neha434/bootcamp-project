package com.springboot.ecommerceApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    JavaMailSender mailSender;

    public void sendForgotPasswordLinkEmail(String email, String token) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Forgot Password Token");
        mail.setText("Please use this link to set new password" + "\r\n" +
                "http://localhost:8080/login/reset-password/?token=" + token);
        mailSender.send(mail);

    }

    public void sendActivationLinkEmail(String email, String token) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Activate Account Token");
        mail.setText("Please use this link to activate your Account" + "\r\n" +
                "http://localhost:8080/register/activate-account?token=" + token);
        mailSender.send(mail);

    }

    public void sendAAccountRegisterEmail(String email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Account Created");
        mail.setText("Your account has been created");
        mailSender.send(mail);

    }
}

