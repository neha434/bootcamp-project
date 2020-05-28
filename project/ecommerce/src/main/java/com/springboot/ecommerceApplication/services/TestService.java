package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    MailService mailService;
    @Autowired
    MessageSource messageSource;

    public List<TestUser> listUsers() {
        List<TestUser> users = new ArrayList<>();

        //create dummy users
        users.add(new TestUser(1, "Jack Lee", "jack@example.com", "Germany", 35));
        users.add(new TestUser(2, "Jovan Srovoki", "jovan@srovoki.me", "Russia", 21));
        users.add(new TestUser(3, "Atta", "atta@gmail.com", "Pakistan", 29));

        return users;
    }

}