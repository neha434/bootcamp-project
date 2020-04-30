package com.springboot.ecommerceApplication.services;


import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.co.SellerCO;
import com.springboot.ecommerceApplication.domain.Role;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.PagingAndSortingDto;
import com.springboot.ecommerceApplication.dto.SellerDto;
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

    public SellerDto registerSeller(SellerCO sellerCO) {
        Seller seller = sellerRepository.findByEmail(sellerCO.getEmail());
        if (seller != null) {
            throw new CustomerAlreadyExistsException("Account Already Exist With This Email Id");
        }
        Seller registerSeller = new Seller();

        registerSeller.setEmail(sellerCO.getEmail());
        registerSeller.setFirstName(sellerCO.getFirstName());
        registerSeller.setMiddleName(sellerCO.getMiddleName());
        registerSeller.setLastName(sellerCO.getLastName());
        registerSeller.setPassword(passwordEncoder.encode(sellerCO.getPassword()));
        registerSeller.setCompanyContact(sellerCO.getCompanyContact());
        List<Role> roleList = new ArrayList<>();

        roleList.add(roleRepository.findByAuthority("ROLE_SELLER"));
        registerSeller.setRoleList(roleList);

        sellerRepository.save(registerSeller);
    mailService.sendAAccountRegisterEmail(registerSeller.getEmail());

        SellerDto sellerDto = getSeller(registerSeller.getId());
        return sellerDto;
    }


    public SellerDto getSeller(Integer id) {

        Optional<Seller> optional = sellerRepository.findById(id);
        if (!optional.isPresent()) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        Seller seller = optional.get();
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

    public List<SellerDto> getAllSeller() {


//        Pageable paging;
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


    public SellerDto updateSeller(Integer id, SellerCO sellerCO) {
        if (!sellerRepository.findById(id).isPresent()) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        Seller seller = sellerRepository.findById(id).get();
        BeanUtils.copyProperties(sellerCO, seller);
        sellerRepository.save(seller);
        SellerDto sellerDto = getSeller(seller.getId());
        return sellerDto;
    }

    public ResponseEntity<String> updateAddress(Integer id, AddressDto addressDto) {
        ResponseEntity<String> responseEntity;
        Optional<Address> optional = addressRepository.findById(id);
       if(!optional.isPresent()){
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


//    public SellerDto updateUser(Integer id, SellerCO sellerCO) {
//        if (!sellerRepository.findById(id).isPresent()) {  //ispresent?
//            throw new AccountDoesNotExists("Invalid Account Credentials");
//        }
//        Seller seller = sellerRepository.findById(id).get();
//        BeanUtils.copyProperties(sellerCO, seller);
//        sellerRepository.save(seller);
//        SellerDto sellerDto = getSeller(seller.getId());
//        return sellerDto;
//    }
      public ResponseEntity<String> updateUser(Integer id, SellerCO sellerCO) {
        {
            ResponseEntity<String> responseEntity;
            Optional<Seller> optional = sellerRepository.findById(id);
            if (!optional.isPresent()) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage
                        ("message-invalid-details", null, LocaleContextHolder.getLocale()));
                return responseEntity;
            }
            Seller seller = sellerRepository.findById(id).get();
            BeanUtils.copyProperties(sellerCO, seller);
            sellerRepository.save(seller);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                    ("message-seller-updated", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }


}}
