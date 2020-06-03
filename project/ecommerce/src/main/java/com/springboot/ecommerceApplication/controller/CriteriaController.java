package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.ResponseDto;
import com.springboot.ecommerceApplication.dto.SuccessDto;
import com.springboot.ecommerceApplication.repositories.UsersRepo;
import com.springboot.ecommerceApplication.services.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriteriaController {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    CriteriaService criteriaService;

    @GetMapping("/getEmails")
    public ResponseEntity<ResponseDto> getEmail(@RequestParam("email") String email)
    {
        return criteriaService.getEmail(email);
    }

}
