package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.domain.Role;
import com.springboot.ecommerceApplication.domain.VerificationToken;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.CustomerDto;
import com.springboot.ecommerceApplication.dto.PagingAndSortingDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    //PasswordEncoder confirmPasswordEncoder = new BCryptPasswordEncoder();
//    public CustomerDto registerCustomer(CustomerCO customerCO){
//        Customer customer = customerRepository.findByEmail(customerCO.getEmail());
//        if(customer != null){
//            throw new CustomerAlreadyExistsException("Account Already Exist With This Email Id");
//        }
//        Customer registerCustomer = new Customer();
//
//        registerCustomer.setEmail(customerCO.getEmail());
//        registerCustomer.setFirstName(customerCO.getFirstName());
//        registerCustomer.setMiddleName(customerCO.getMiddleName());
//        registerCustomer.setLastName(customerCO.getLastName());
//        registerCustomer.setPassword(passwordEncoder.encode(customerCO.getPassword()));
//// registerCustomer.setPassword((confirmPasswordEncoder.encode(customerCO.getConfirmPassword())));
//        registerCustomer.setContact(customerCO.getContact());
//        List<Role> roleList = new ArrayList<>();
//
//        roleList.add(roleRepository.findByAuthority("ROLE_CUSTOMER"));
//        registerCustomer.setRoleList(roleList);
//
//        customerRepository.save(registerCustomer);
//
//        CustomerDto customerDto = getCustomer(registerCustomer.getId());
//        return customerDto;
//    }
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
        createVerificationToken(registerUser, token);
        mailService.sendActivationLinkEmail(registerUser.getEmail(), token);

        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-account-created", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

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

    public ResponseEntity<String> updateUser(Integer id, CustomerCO customerCO) {
        {
            ResponseEntity<String> responseEntity;
            Optional<Customer> optional = customerRepository.findById(id);
            if (!optional.isPresent()) {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage
                        ("message-invalid-details", null, LocaleContextHolder.getLocale()));
                return responseEntity;
            }
            Customer customer = customerRepository.findById(id).get();
            BeanUtils.copyProperties(customerCO, customer);
            customerRepository.save(customer);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                    ("message-customer-updated", null, LocaleContextHolder.getLocale()));
            return responseEntity;
        }

    }


//    public Map<String,Boolean> deleteCustomer(Integer id){
//        Map<String,Boolean> map = new HashMap<>();
//        Optional<Customer> optional = customerRepository.findById(id);
//
//        if(!optional.isPresent()){
//            map.put("Deleted",false);
//        }
//        else {
//            customerRepository.deleteById(id);
//            map.put("Deleted",true);
//        }    private static final Logger logger = LoggerFactory.getLogger(LoginSchedular.class);

//        return map;
//    }

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

    public void createVerificationToken(Customer registerUser, String token) {
        VerificationToken newToken = new VerificationToken(token, registerUser);
        verificationTokenRepository.save(newToken);
    }

}


