package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepository;

    public String changeRoleOfUser(Integer id, String role) {
        User user = userRepository.findById(id).get();
        user.getRoleList().get(0).setAuthority(role);
        userRepository.save(user);
        return "Role is changed";

    }

    public ResponseEntity activateById(Integer id, WebRequest webRequest){
        Optional<User> user=userRepository.findById(id);
        String message;
        ResponseEntity responseEntity;
        if(!user.isPresent()){
            message="No user found with the given id";
            responseEntity=new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }
        else{
            User savedUser=user.get();
            if(savedUser.isActive()){
                message="User already active";
                responseEntity=new ResponseEntity(message,HttpStatus.BAD_REQUEST);

            }
            else {
                savedUser.setActive(true);
                userRepository.save(savedUser);
                message = "User activated";
                responseEntity = new ResponseEntity(message, HttpStatus.OK);
            }

        }
        return  responseEntity;
    }
    public  ResponseEntity deActivateById(Integer id, WebRequest webRequest){
        String message;
        Optional<User> user=userRepository.findById(id);
        ResponseEntity responseEntity;
        if (!user.isPresent())
        {
            message = "No user found with the given id";
            responseEntity = new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }
        else
        {
            User savedUser=user.get();
            if(!savedUser.isActive()){
                message="User already deactivated";
                responseEntity=new ResponseEntity(message,HttpStatus.BAD_REQUEST);

            }
            else {
                savedUser.setActive(false);
                userRepository.save(savedUser);
                message="User Deactivated";
                responseEntity = new ResponseEntity(message, HttpStatus.OK);

            }

        }
        return responseEntity;
    }

}
