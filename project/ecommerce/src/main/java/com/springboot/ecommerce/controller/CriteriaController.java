package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.ResponseDto;
import com.springboot.ecommerce.repositories.UsersRepo;
import com.springboot.ecommerce.services.CriteriaService;
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
