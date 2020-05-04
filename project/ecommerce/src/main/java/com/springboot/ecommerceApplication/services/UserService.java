package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.domain.Role;
import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.UserDto;
import com.springboot.ecommerceApplication.enums.UserRole;
import com.springboot.ecommerceApplication.exception.AccountActivationException;
import com.springboot.ecommerceApplication.exception.AccountDoesNotExists;
import com.springboot.ecommerceApplication.repositories.ProductVariationRepo;
import com.springboot.ecommerceApplication.repositories.SellerRepo;
import com.springboot.ecommerceApplication.repositories.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    @Autowired
    MailService mailService;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ProductVariationRepo productVariationRepository;
    @Autowired
    SellerRepo sellerRepository;


    public String changeRoleOfUser(Integer id, UserRole role) {
        User user = userRepository.findById(id).get();
        user.getRoleList().get(0).setAuthority(role.name());
        userRepository.save(user);
        return "Role is changed";

    }

    public ResponseEntity activateDeactivateById(Integer id, Boolean isActive) {
        validationUser(id, isActive);
        User savedUser = userRepository.findById(id).get();
        String message;
        ResponseEntity responseEntity;
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

    public ResponseEntity<String> updateUser(Integer id, UserDto userDto) {

            ResponseEntity<String> responseEntity;
            Optional<User> optional = userRepository.findById(id);
            if (!optional.isPresent()) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage
                        ("message-invalid-details", null, LocaleContextHolder.getLocale()));
                return responseEntity;
            }
            User user = userRepository.findById(id).get();
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

    public ResponseEntity activateDeactivateProductById(Integer id, boolean isActive) {
        validationProduct(id, isActive);
        ProductVariation savedProduct = productVariationRepository.findById(id).get();
        String productName = savedProduct.getProduct().getName();
        String email = savedProduct.getProduct().getSeller().getEmail();
        String message;
        ResponseEntity responseEntity;
        if ((!isActive)) {
            savedProduct.setActive(false);
            productVariationRepository.save(savedProduct);
            message = "Product Deactivated";
            responseEntity = new ResponseEntity(message, HttpStatus.OK);
            mailService.sendProductDeactivationEmail(email,productName);

        } else {
            savedProduct.setActive(true);
            productVariationRepository.save(savedProduct);
            message = "Product activated";
            responseEntity = new ResponseEntity(message, HttpStatus.OK);
           mailService.sendProductActivationEmail(email,productName);

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



