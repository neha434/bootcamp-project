package com.springboot.ecommerce.services;

import com.springboot.ecommerce.domain.product.ProductVariation;
import com.springboot.ecommerce.domain.user.User;
import com.springboot.ecommerce.dto.UserDto;
import com.springboot.ecommerce.enums.UserRole;
import com.springboot.ecommerce.exception.AccountActivationException;
import com.springboot.ecommerce.exception.AccountDoesNotExists;
import com.springboot.ecommerce.repositories.ProductVariationRepo;
import com.springboot.ecommerce.repositories.SellerRepo;
import com.springboot.ecommerce.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepository;
    @Autowired
    MailService mailService;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ProductVariationRepo productVariationRepository;
    @Autowired
    SellerRepo sellerRepository;
    @Autowired
    UserRepo userRepo;


    public String changeRoleOfUser(String username, Integer id, UserRole role) {
        User users = userRepo.findByEmail(username);
        User user = userRepository.findById(id).get();
        user.getRoleList().get(0).setAuthority(role.name());
        userRepository.save(user);
        return "Role is changed";

    }

    public ResponseEntity activateDeactivateById(String username, Integer id, Boolean isActive) {
        User user = userRepo.findByEmail(username);
        ResponseEntity responseEntity = null;
        validationUser(id, isActive);
        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()){
        User savedUser = optionalUser.get();
        String message;
        if ((!isActive)) {
            savedUser.setActive(false);
            userRepository.save(savedUser);
            message = "User Deactivated";
            responseEntity = new ResponseEntity(message, HttpStatus.OK);
            mailService.sendAccountDeactivationEmail(savedUser.getEmail());

        } else {
            savedUser.setActive(true);
            userRepository.save(savedUser);
            message = "User activated";
            responseEntity = new ResponseEntity(message, HttpStatus.OK);
            mailService.sendAccountActivationEmail(savedUser.getEmail());

        }
        }

        return responseEntity;
    }

    public void validationUser(Integer id, Boolean isActive) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new AccountDoesNotExists("The given email is not registered");

        } else {
            User savedUser = user.get();
            if ((savedUser.isActive()) && (isActive)) {
                throw new AccountActivationException("User is already active");
            } else if ((!savedUser.isActive()) && (!isActive)) {
                throw new AccountActivationException("User is already deactivated");
            }
        }
    }

    public ResponseEntity<String> updateUser(String username, UserDto userDto) {
        User user = userRepo.findByEmail(username);
        ResponseEntity<String> responseEntity;
       // Optional<User> optional = userRepository.findById(id);
        if (user==null) {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage
                    ("message-invalid-details", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }
       // User user = userRepository.findById(id).get();
        if (userDto.getEmail() != null)
            user.setEmail((userDto.getEmail()));
        if (userDto.getFirstName() != null)
            user.setFirstName((userDto.getFirstName()));
        if (userDto.getMiddleName() != null)
            user.setMiddleName((userDto.getMiddleName()));
        if (userDto.getLastName() != null)
            user.setLastName((userDto.getLastName()));
        userRepository.save(user);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-admin-updated", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    public ResponseEntity activateDeactivateProductById(String username, Integer id, boolean isActive) {
        User user = userRepo.findByEmail(username);
        validationProduct(id, isActive);

        ResponseEntity responseEntity = null;

        Optional<ProductVariation> productVariationOptional =productVariationRepository.findById(id);
        if(productVariationOptional.isPresent()){
        ProductVariation savedProduct = productVariationOptional.get();
        String productName = savedProduct.getProduct().getName();
        String email = savedProduct.getProduct().getSeller().getEmail();
        String message;
        if ((!isActive)) {
            savedProduct.setActive(false);
            productVariationRepository.save(savedProduct);
            message = "Product Deactivated";
            responseEntity = new ResponseEntity(message, HttpStatus.OK);
            mailService.sendProductDeactivationEmail(email, productName);

        } else {
            savedProduct.setActive(true);
            productVariationRepository.save(savedProduct);
            message = "Product activated";
            responseEntity = new ResponseEntity(message, HttpStatus.OK);
            mailService.sendProductActivationEmail(email, productName);

        }
        }
        return responseEntity;
    }

    public void validationProduct(Integer id, Boolean isActive) {
        Optional<ProductVariation> productVariation = productVariationRepository.findById(id);
        if (!productVariation.isPresent()) {
            throw new AccountDoesNotExists("The given product is not present");

        } else {
            ProductVariation savedProduct = productVariation.get();
            if ((savedProduct.isActive()) && (isActive)) {
                throw new AccountActivationException("Product already active");
            } else if ((!savedProduct.isActive()) && (!isActive)) {
                throw new AccountActivationException("Product already deactivated");
            }
        }
    }

}



