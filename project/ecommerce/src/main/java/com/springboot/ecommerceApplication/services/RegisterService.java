package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.co.SellerCO;
import com.springboot.ecommerceApplication.domain.Role;
import com.springboot.ecommerceApplication.domain.VerificationToken;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.exception.CustomerAlreadyExistsException;
import com.springboot.ecommerceApplication.exception.SellerAlreadyExistsException;
import com.springboot.ecommerceApplication.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class RegisterService {
    @Autowired
    VerificationTokenRepo verificationTokenRepository;
    @Autowired
    UserRepo userRepository;
    @Autowired
    MailService mailService;
    @Autowired
    MessageSource messageSource;
    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    RoleRepo roleRepository;
    @Autowired
    SellerRepo sellerRepository;
    @Autowired
    AddressRepository addressRepo;

    //to register a customer
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<String> registerCustomer(CustomerCO customerCO) {
        Customer customer = customerRepository.findByEmail(customerCO.getEmail());
        ResponseEntity<String> responseEntity;
        if (customer != null) {
            throw new CustomerAlreadyExistsException("Account Already Exist With This Email Id");
        }
        Customer registerUser = new Customer(customerCO.getEmail(), customerCO.getFirstName(), customerCO.getMiddleName(),
                customerCO.getLastName(), passwordEncoder.encode(customerCO.getPassword()), customerCO.getContact());

        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepository.findByAuthority("ROLE_CUSTOMER"));
        registerUser.setRoleList(roleList);
        customerRepository.save(registerUser);
        String token = UUID.randomUUID().toString();
        createVerificationTokenCustomer(registerUser, token);
        mailService.sendActivationLinkEmail(registerUser.getEmail(), token);

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-account-created", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    public void createVerificationTokenCustomer(User registerUser, String token) {
        VerificationToken newToken = new VerificationToken(token, registerUser);
        verificationTokenRepository.save(newToken);
    }


    //activation of account
    public ResponseEntity<String> activateAccount(String token) {
        VerificationToken verificationToken = getVerificationToken(token);
        ResponseEntity<String> responseEntity;
        if (verificationToken == null) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
                    ("message-invalid-verification-token", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        //token is exired
        if (verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage
                    ("message-verification-token-expired", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }
        verificationTokenRepository.delete(verificationToken);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-account-verified", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }

    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }


    public ResponseEntity<String> registerSeller(SellerCO sellerCO) {
        Seller seller = sellerRepository.findByEmail(sellerCO.getEmail());
        ResponseEntity<String> responseEntity;
        if (seller != null) {
            throw new SellerAlreadyExistsException("Account Already Exist With This Email Id");
        }

        Seller registerSeller = new Seller(sellerCO.getEmail(), sellerCO.getFirstName(), sellerCO.getMiddleName(),
                sellerCO.getLastName(), passwordEncoder.encode(sellerCO.getPassword()), sellerCO.getGst(), sellerCO.getCompanyName(),
                sellerCO.getCompanyContact());

        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepository.findByAuthority("ROLE_SELLER"));
        registerSeller.setRoleList(roleList);
        sellerRepository.save(registerSeller);
        setRegisteredSellerAddress(sellerCO, registerSeller.getId());

        String token = UUID.randomUUID().toString();
        createActivationTokenSeller(token, registerSeller);
        mailService.sendActivateAccountLinkEmailSeller(registerSeller.getEmail(), token);

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-account-created", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    public void setRegisteredSellerAddress(SellerCO sellerCO, Integer id) {
        Address address = new Address(sellerCO.getState(), sellerCO.getCountry(), sellerCO.getCity(),
                sellerCO.getAddressLine(), sellerCO.getLabel(), sellerCO.getZipCode());
        address.setUser(sellerRepository.findById(id).get());
        addressRepo.save(address);
    }

    private void createActivationTokenSeller(String token, Seller registerSeller) {
        VerificationToken newToken = new VerificationToken(token, registerSeller);
        verificationTokenRepository.save(newToken);


    }

}
