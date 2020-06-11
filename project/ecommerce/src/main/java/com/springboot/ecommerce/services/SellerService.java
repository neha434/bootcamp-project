package com.springboot.ecommerce.services;


import com.springboot.ecommerce.domain.user.Address;
import com.springboot.ecommerce.domain.user.Seller;
import com.springboot.ecommerce.domain.user.User;
import com.springboot.ecommerce.dto.AddressDto;
import com.springboot.ecommerce.dto.PasswordDto;
import com.springboot.ecommerce.dto.SellerDto;
import com.springboot.ecommerce.exception.AccountDoesNotExists;
import com.springboot.ecommerce.repositories.AddressRepository;
import com.springboot.ecommerce.repositories.RoleRepo;
import com.springboot.ecommerce.repositories.SellerRepo;
import com.springboot.ecommerce.repositories.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    UserRepo userRepo;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //.........TO VIEW PROFILE.....................
    @Cacheable(value = "getSellerCache", key = "#root.methodName")
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
    @Cacheable(value = "sellerCache", key = "#root.methodName")
    public List<SellerDto> getAllSeller(String username) {
        User user = userRepo.findByEmail(username);
        Iterable<Seller> sellers = sellerRepository.findAll();
        List<SellerDto> sellerDtoList = new ArrayList<>();
        sellers.forEach(sellers1 -> sellerDtoList.add(new SellerDto(sellers1.getId(), sellers1.getEmail(),
                sellers1.getFirstName(), sellers1.getMiddleName(), sellers1.getLastName(), sellers1.getGst(),
                sellers1.getCompanyContact(), sellers1.getCompanyName())));
        System.out.println("list of sellers");
        return sellerDtoList;
    }

    //..............UPDATE SELLER ADDRESS.......................
    public ResponseEntity<String> updateSellerAddress(String username, Integer id, AddressDto addressDto) {
        Seller seller = sellerRepository.findByEmail(username);
        ResponseEntity<String> responseEntity = null;
        Optional<Address> optional = addressRepository.findById(id);
        if (!optional.isPresent()) {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage
                    ("message-invalid-details", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }

        Optional<Address> optionalAddress= addressRepository.findById(id);
        if(optionalAddress.isPresent()) {
            Address address = optionalAddress.get();

            BeanUtils.copyProperties(addressDto, address);
            addressRepository.save(address);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                    ("message-address-updated", null, LocaleContextHolder.getLocale()));
        }
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
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-seller-updated", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    //..........TO UPDATE PASSWORD...................
    public ResponseEntity<String> updatePassword(String username, PasswordDto passwordDto) {
        Seller seller = sellerRepository.findByEmail(username);
        ResponseEntity<String> responseEntity;
        String newPassword = passwordDto.getPassword();
        String confirmPassword = passwordDto.getConfirmPassword();
        if (newPassword.equals(confirmPassword)) {
            seller.setPassword(passwordEncoder.encode(newPassword));
            sellerRepository.save(seller);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                    ("message-password-updated", null, LocaleContextHolder.getLocale()));
            mailService.sendPasswordChangedDetail(username);
            return responseEntity;
        } else
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                    ("message-password-not-matched", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }


}
