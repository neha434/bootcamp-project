package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.domain.Role;
import com.springboot.ecommerceApplication.domain.VerificationToken;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.exception.AccountDoesNotExists;
import com.springboot.ecommerceApplication.exception.CustomerAlreadyExistsException;
import com.springboot.ecommerceApplication.repositories.AddressRepository;
import com.springboot.ecommerceApplication.repositories.CustomerRepo;
import com.springboot.ecommerceApplication.repositories.RoleRepo;
import com.springboot.ecommerceApplication.repositories.VerificationTokenRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    //Api to get my detail
    public CustomerDto getCustomer(Integer id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (!optional.isPresent()) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        Customer customer = optional.get();
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

    public List<CustomerDto> getAllCustomer() {
//        Pageable paging;
//        if (pagingAndSortingDto == null) {
//            paging = PageRequest.of(0, 10, Sort.by("id").ascending());
//        } else {
//            if (pagingAndSortingDto.getOrder() == "descending")
//                paging = PageRequest.of(pagingAndSortingDto.getMax(), pagingAndSortingDto.getOffset(),
//                        Sort.by(pagingAndSortingDto.getSortField()).descending());
//            else
//                paging = PageRequest.of(pagingAndSortingDto.getMax(), pagingAndSortingDto.getOffset(),
//                        Sort.by(pagingAndSortingDto.getSortField()).ascending());
//        }
//        List<Customer> pagedResult = customerRepository.findAll(paging);

        Iterable<Customer> customersList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customersList.forEach(customers -> customerDtoList.add(new CustomerDto(customers.getId(), customers.getEmail(),
                customers.getFirstName(),
                customers.getMiddleName(), customers.getLastName(), customers.getContact())));
        return customerDtoList;
    }
// to update customer profile
    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto, boolean isPatch) {
        if (!customerRepository.findById(id).isPresent()) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        Customer customer = customerRepository.findById(id).get();
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
        CustomerDto customerDto1 = getCustomer(customer.getId());
        return customerDto1;
    }


    public AddressDto getAddress(Integer id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (!optional.isPresent()) {
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        Address address = optional.get();
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        addressDto.setAddressLine(address.getAddressLine());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setLabel(address.getLabel());
        return addressDto;
    }


    public Map<String, Boolean> deleteAddress(Integer id) {
        Map<String, Boolean> map = new HashMap<>();
        Optional<Address> optional = addressRepository.findById(id);
        if (!optional.isPresent()) {
            map.put("Deleted", false);
        } else {
            addressRepository.deleteById(id);
            map.put("Deleted", true);
        }
        return map;
    }

    public String AddAddress(Integer id, AddressDto addressDto) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (!optional.isPresent()) {
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
        AddressDto addressDto1 = getAddress(address.getId());
        return "Address added successfully";
    }

    public ResponseEntity<String> updateCustomerAddress(Integer id, AddressDto addressDto) {
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



}



//public Integer getCurrentlyLoggedInUserId(WebRequest webRequest){
//Authentication auth=  (Authentication) webRequest.getUserPricipal();
//return userRepository.findByEmail(auth.getName()).getId();
//}
