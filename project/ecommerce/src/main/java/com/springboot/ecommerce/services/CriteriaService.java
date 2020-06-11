package com.springboot.ecommerce.services;

import com.springboot.ecommerce.domain.user.User;
import com.springboot.ecommerce.dto.ResponseDto;
import com.springboot.ecommerce.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CriteriaService {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    MessageSource messageSource;

    public ResponseEntity<ResponseDto> getEmail(String email) {
        User user= usersRepo.findByEmails(email);
        String username = user.getEmail();
        ResponseEntity<ResponseDto> responseEntity;
        ResponseDto responseDto = new ResponseDto();
         responseDto.setResult(username);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(responseDto);
        return responseEntity;

    }

}


