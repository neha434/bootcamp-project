package com.springboot.ecommerce.services;

import com.springboot.ecommerce.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

    //by admin
    public void sendAccountActivationEmail(String email) {
        mail.setTo(email);
        mail.setSubject("Account Activation");
        mail.setText("Your account has been activated");
        mailSender.send(mail);
    }
//by admin
    public void sendAccountDeactivationEmail(String email) {
        mail.setTo(email);
        mail.setSubject("Account Deactivated");
        mail.setText("Your account has been deactivated");
        mailSender.send(mail);
    }
//by admin
    public void sendProductDeactivationEmail(String email, String productName) {
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

    public void sendPasswordChangedDetail(String username) {
        mail.setTo(username);
        mail.setSubject("Password updated");
        mail.setText("Your password has been updated");
        mailSender.send(mail);
    }
    @Async
    public void sendAddedProductDetailsEmail(Product product) {
        mail.setTo("neha.rai@tothenew.com");
        mail.setSubject("Product Added");
        mail.setText("Name of Product Added:  "+ product.getName() + "," + "   " + "Id of Product for activation:  " + product.getId());
        mailSender.send(mail);

    }

    public void sendProductDetails(String email, Integer id, String currentTime) throws Exception {
        mail.setTo(email);
        mail.setSubject("ProductAdded");
        mail.setText("Open the provided to link to get the details of added products"+ "  " +
                "http://localhost:8080/export-product-details?id=" +id +"&date=" + URLEncoder.encode(currentTime, StandardCharsets.UTF_8.toString()) );
        mailSender.send(mail);
    }
}
