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

    public void sendActivateAccountLinkEmail(String email, String token) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Forgot Password Token");
        mail.setText("Please use this link to set new password" + "\r\n" +
                "http://localhost:8080/login/reset-password/?token=" + token);
        mailSender.send(mail);


    }
}
