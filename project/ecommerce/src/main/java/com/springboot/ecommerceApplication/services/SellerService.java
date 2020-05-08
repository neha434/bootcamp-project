package com.springboot.ecommerceApplication.services;


import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.co.SellerCO;
import com.springboot.ecommerceApplication.domain.Role;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.dto.*;
import com.springboot.ecommerceApplication.exception.AccountDoesNotExists;
import com.springboot.ecommerceApplication.exception.CustomerAlreadyExistsException;
import com.springboot.ecommerceApplication.repositories.AddressRepository;
import com.springboot.ecommerceApplication.repositories.RoleRepo;
import com.springboot.ecommerceApplication.repositories.SellerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class SellerService {
    @Autowired
    MessageSource messageSource;
    MailService mailService;
    @Autowired
    SellerRepo sellerRepository;
    AddressRepository addressRepository;
    @Autowired
    RoleRepo roleRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //.........TO VIEW PROFILE.....................
    public SellerDto getSeller(String username) {
        Seller seller = sellerRepository.findByEmail(username);
        if (seller == null) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        // Seller seller = optional.get();
        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(seller.getId());
        sellerDto.setEmail(seller.getEmail());
        sellerDto.setFirstName(seller.getFirstName());
        sellerDto.setFirstName(seller.getFirstName());
        sellerDto.setLastName(seller.getLastName());
        sellerDto.setCompanyContact(seller.getCompanyContact());
        sellerDto.setCompanyName(seller.getCompanyName());
        sellerDto.setGst(seller.getGst());

        return sellerDto;
    }

   //.............TO GET LIST OF SELLERS...................
    public List<SellerDto> getAllSeller() {
        // Pageable paging;
//        if (pagingAndSortingDto == null){
//            paging = PageRequest.of(0, 10, Sort.by("id").ascending());
//        }
//        else {
//            if (pagingAndSortingDto.getOrder() == "descending")
//                paging=PageRequest.of(pagingAndSortingDto.getMax(), pagingAndSortingDto.getOffset(),
//                        Sort.by(pagingAndSortingDto.getSortField()).descending());
//            else
//                paging=PageRequest.of(pagingAndSortingDto.getMax(), pagingAndSortingDto.getOffset(),
//                        Sort.by(pagingAndSortingDto.getSortField()).ascending());
//        }

        // List<Seller> pagedResult = sellerRepository.findAll(pageable);

        Iterable<Seller> sellers = sellerRepository.findAll();
        List<SellerDto> sellerDtoList = new ArrayList<>();
        sellers.forEach(sellers1 -> sellerDtoList.add(new SellerDto(sellers1.getId(), sellers1.getEmail(),
                sellers1.getFirstName(), sellers1.getMiddleName(), sellers1.getLastName(), sellers1.getGst(),
                sellers1.getCompanyContact(), sellers1.getCompanyName())));
        return sellerDtoList;
    }

   //..............UPDATE SELLER ADDRESS.......................
    public ResponseEntity<String> updateSellerAddress(String username,Integer id, AddressDto addressDto) {
        Seller seller = sellerRepository.findByEmail(username);
        ResponseEntity<String> responseEntity;
        Optional<Address> optional = addressRepository.findById(id);
        if (!optional.isPresent()) {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage
                    ("message-invalid-details", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }
        Address address = addressRepository.findById(id).get();
        BeanUtils.copyProperties(addressDto, address);
        addressRepository.save(address);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-address-updated", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }

    //........TO UPDATE PROFILE......................

    public ResponseEntity<String> updateCustomer(String username, SellerDto sellerDto, boolean isPatch) {
        if (sellerRepository.findByEmail(username) == null) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        ResponseEntity<String> responseEntity;
        Seller seller = sellerRepository.findByEmail(username);
        if ((isPatch && sellerDto.getEmail() != null) || (!isPatch))
            seller.setEmail((sellerDto.getEmail()));
        if ((isPatch && sellerDto.getFirstName() != null) || (!isPatch))
            seller.setFirstName((sellerDto.getFirstName()));
        if ((isPatch && sellerDto.getMiddleName() != null) || (!isPatch))
            seller.setMiddleName((sellerDto.getMiddleName()));
        if ((isPatch && sellerDto.getLastName() != null) || (!isPatch))
            seller.setLastName((sellerDto.getLastName()));
        if ((isPatch && sellerDto.getCompanyContact() != null) || (!isPatch))
            seller.setCompanyContact((sellerDto.getCompanyContact()));
        if ((isPatch && sellerDto.getCompanyName() != null) || (!isPatch))
            seller.setCompanyName((sellerDto.getCompanyName()));
        if ((isPatch && sellerDto.getGst() != null) || (!isPatch))
            seller.setGst((sellerDto.getGst()));
        sellerRepository.save(seller);
        // CustomerDto customerDto1 = getCustomer(customer.getId());
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-seller-updated", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    //..........TO UPDATE PASSWORD...................
    public ResponseEntity<String> updatePassword(String username, PasswordDto passwordDto){
        Seller seller = sellerRepository.findByEmail(username);
        ResponseEntity<String> responseEntity;
        String newPassword = passwordDto.getPassword();
        String confirmPassword= passwordDto.getConfirmPassword();
        if(newPassword.equals(confirmPassword)){
            seller.setPassword(passwordEncoder.encode(newPassword));
            sellerRepository.save(seller);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                    ("message-password-updated", null, LocaleContextHolder.getLocale()));
            mailService.sendPasswordChangedDetail(username);
            return responseEntity;
        }
        else
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                    ("message-password-not-matched", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }



}
