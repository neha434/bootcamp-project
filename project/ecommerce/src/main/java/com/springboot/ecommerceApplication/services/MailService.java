package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    JavaMailSender mailSender;

    SimpleMailMessage mail = new SimpleMailMessage();

    public void sendForgotPasswordLinkEmail(String email, String token) {
       // SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Forgot Password Token");
        mail.setText("Please use this link to set new password" + "\r\n" +
                "http://localhost:8080/login/reset-password/?token=" + token);
        mailSender.send(mail);

    }
//account activation token has been sent to customer
    public void sendActivationLinkEmail(String email, String token) {
        mail.setTo(email);
        mail.setSubject("Activate  Customer Account Token");
        mail.setText("Please use this link to activate your Account" + "\r\n" +
                "http://localhost:8080/activate-user-account?token=" + token);
        mailSender.send(mail);

    }

//    public void sendAAccountRegisterEmail(String email) {
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo(email);
//        mail.setSubject("Account Created");
//        mail.setText("Your account has been created");
//        mailSender.send(mail);
//
//    }
//by admin
    public void sendAccountActivationEmail(String email) {
        mail.setTo(email);
        mail.setSubject("Account Activation");
        mail.setText("Your account has been activated");
        mailSender.send(mail);
    }
//by admin
    public void sendAccountDeactivationEmail(String email) {
       // SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Account Deactivated");
        mail.setText("Your account has been deactivated");
        mailSender.send(mail);
    }
//by admin
    public void sendProductDeactivationEmail(String email, String productName) {
        //SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Product Deactivated");
        mail.setText(productName+"has been deactivated");
        mailSender.send(mail);
    }
//by admin
    public void sendProductActivationEmail(String email, String productName) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Product Activated");
        mail.setText(productName+"has been activated");
        mailSender.send(mail);

    }
//to send activation token for seller
    public void sendActivateAccountLinkEmailSeller(String email, String token) {
      //  SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Activate  Seller Account Token");
        mail.setText("Please use this link to activate your Account" + "\r\n" +
                "http://localhost:8080/activate-user-account?token=" + token);
        mailSender.send(mail);

    }

    public void sendProductDetailEmail(String email, String productName) {
        mail.setTo(email);
        mail.setSubject("About Product quantity details");
        mail.setText("Following products are out of stock and hence are deactivated. Kindly update these products"+productName);
        mailSender.send(mail);

    }

}

