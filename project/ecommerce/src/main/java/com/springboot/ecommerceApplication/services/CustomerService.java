package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.PasswordDto;
import com.springboot.ecommerceApplication.exception.AccountDoesNotExists;
import com.springboot.ecommerceApplication.repositories.*;
import org.bouncycastle.crypto.signers.ISOTrailers;
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

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    VerificationTokenRepo verificationTokenRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    MailService mailService;
    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    RoleRepo roleRepository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    EntityManager entityManager;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //................TO VIEW PROFILE..................
    @Cacheable(value = "getCustomerCache", key = "#root.methodName")
    public CustomerDto getCustomer(String username) {
        Customer customer = customerRepository.findByEmail(username);
        if (customer == null) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setEmail(customer.getEmail());
        customerDto.setFirstName(customer.getFirstName());
        if (customer.getMiddleName() != null) {
            customerDto.setMiddleName(customer.getMiddleName());
        }
        customerDto.setLastName(customer.getLastName());
        customerDto.setContact(customer.getContact());
        customerDto.setActive(customer.getActive());
        return customerDto;
    }

    //..............TO VIEW LIST OF CUSTOMERS.........................
   @Cacheable(value = "customerCache", key = "#root.methodName")
    public List<CustomerDto> getAllCustomer(String username) {
        User user = userRepo.findByEmail(username);

        Iterable<Customer> customersList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customersList.forEach(customers -> customerDtoList.add(new CustomerDto(customers.getId(), customers.getEmail(),
                customers.getFirstName(),
                customers.getMiddleName(), customers.getLastName(), customers.getContact(), customers.getActive())));
        return customerDtoList;
    }


    //...........TO UPDATE PROFILE............................
    public ResponseEntity<String> updateCustomer(String username, CustomerDto customerDto, boolean isPatch) {
        if (customerRepository.findByEmail(username) == null) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        ResponseEntity<String> responseEntity;
        Customer customer = customerRepository.findByEmail(username);
        if ((isPatch && customerDto.getEmail() != null) || (!isPatch))
            customer.setEmail((customerDto.getEmail()));
        if ((isPatch && customerDto.getFirstName() != null) || (!isPatch))
            customer.setFirstName((customerDto.getFirstName()));
        if ((isPatch && customerDto.getMiddleName() != null) || (!isPatch))
            customer.setMiddleName((customerDto.getMiddleName()));
        if ((isPatch && customerDto.getLastName() != null) || (!isPatch))
            customer.setLastName((customerDto.getLastName()));
        if ((isPatch && customerDto.getContact() != null) || (!isPatch))
            customer.setContact((customerDto.getContact()));
        customerRepository.save(customer);
        // CustomerDto customerDto1 = getCustomer(customer.getId());
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-customer-updated", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    // view address
    public List<AddressDto> getAddress(String username) {
        Customer customer = customerRepository.findByEmail(username);
        //  Optional<Address> optional = addressRepository.findByEmail(username);
        if (customer == null) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        Iterable<Address> addressIterable = customer.getAddressesList();
        // Address address = optional.get();
        List<AddressDto> addressDtoList = new ArrayList<>();
        addressIterable.forEach(address -> addressDtoList.add(new AddressDto(address.getId(), address.getCity(),
                address.getState(), address.getCountry(),
                address.getAddressLine(), address.getZipCode(), address.getLabel())));
        return addressDtoList;
    }

    //add address
    public String AddAddress(String username, AddressDto addressDto) {
        Customer customer = customerRepository.findByEmail(username);
        if (customer == null) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setAddressLine(addressDto.getAddressLine());
        address.setZipCode(addressDto.getZipCode());
        address.setLabel(addressDto.getLabel());
        addressRepository.save(address);
        //  AddressDto addressDto1 = getAddress(address.getId());
        return "Address added successfully";
    }

    //update address
    public ResponseEntity<String> updateCustomerAddress(String username, Integer id, AddressDto addressDto) {
        Customer customer = customerRepository.findByEmail(username);

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

    //delete address
    public ResponseEntity<String> deleteAddress(String username, Integer id) {
        Customer customer = customerRepository.findByEmail(username);

        Optional<Address> addressOptional = addressRepository.findById(id);
        if (!addressOptional.isPresent()) {
            return new ResponseEntity<>("No address found with the given id", HttpStatus.NOT_FOUND);
        }
        Address savedAddress = addressOptional.get();
        if (savedAddress.getUser().getEmail().equals(username)) {
            addressRepository.deleteById(id);
            return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No operation performed", HttpStatus.BAD_REQUEST);
    }

    //..........TO UPDATE PASSWORD...................
    public ResponseEntity<String> updatePassword(String username, PasswordDto passwordDto) {
        Customer customer = customerRepository.findByEmail(username);
        ResponseEntity<String> responseEntity;
        String newPassword = passwordDto.getPassword();
        String confirmPassword = passwordDto.getConfirmPassword();
        if (newPassword.equals(confirmPassword)) {
            customer.setPassword(passwordEncoder.encode(newPassword));
            customerRepository.save(customer);
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


