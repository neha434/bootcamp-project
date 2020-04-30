package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.Role;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.enums.UserRole;
import com.springboot.ecommerceApplication.exception.AccountDoesNotExists;
import com.springboot.ecommerceApplication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepository;

    public String changeRoleOfUser(Integer id, UserRole role) { //use enum
        User user = userRepository.findById(id).get();
        user.getRoleList().get(0).setAuthority(role.name());
        userRepository.save(user);
        return "Role is changed";

    }
    public ResponseEntity activateDeactivateById(Integer id, Boolean isActive) {
        ResponseEntity responseEntity;
        responseEntity = validation(id, isActive);
        User savedUser = userRepository.findById(id).get();
        String message;
       // User savedUser = user.get();
        if ((!isActive)) {
            savedUser.setActive(false);
            userRepository.save(savedUser);
            message = "User Deactivated";
            responseEntity = new ResponseEntity(message, HttpStatus.OK);

        } else {
            savedUser.setActive(true);
            userRepository.save(savedUser);
            message = "User activated";
            responseEntity = new ResponseEntity(message, HttpStatus.OK);
        }

        return responseEntity;
    }
    public ResponseEntity validation(Integer id, Boolean isActive) {
        Optional<User> user = userRepository.findById(id);
        String message;
        User savedUser = user.get();
        ResponseEntity responseEntityValidation = null;
        if (!user.isPresent()) {
            message = "No user found with the given id";
            responseEntityValidation = new ResponseEntity(message, HttpStatus.NOT_FOUND);
        } else {
            if ((savedUser.isActive())&&(isActive)) {
                message = "User already active";
                responseEntityValidation = new ResponseEntity(message, HttpStatus.BAD_REQUEST);
            } else if((!savedUser.isActive())&&(!isActive)){
                message = "User already deactivated";
                responseEntityValidation = new ResponseEntity(message, HttpStatus.BAD_REQUEST);
            }
        }
        return  responseEntityValidation;
    }
}



