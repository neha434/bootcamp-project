package com.springboot.ecommerceApplication.services;


import com.springboot.ecommerceApplication.co.SellerCO;
import com.springboot.ecommerceApplication.domain.Role;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.dto.SellerDto;
import com.springboot.ecommerceApplication.exception.AccountDoesNotExists;
import com.springboot.ecommerceApplication.exception.CustomerAlreadyExistsException;
import com.springboot.ecommerceApplication.repositories.AddressRepository;
import com.springboot.ecommerceApplication.repositories.RoleRepo;
import com.springboot.ecommerceApplication.repositories.SellerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellerService {
    @Autowired
    SellerRepo sellerRepository;
    AddressRepository addressRepository;
    @Autowired
    RoleRepo roleRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SellerDto registerSeller(SellerCO sellerCO){
        Seller  seller = sellerRepository.findByEmail(sellerCO.getEmail());
        if(seller != null){
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

        SellerDto sellerDto = getSeller(registerSeller.getId());
        return sellerDto;
    }



    public SellerDto getSeller(Integer id){

        Optional<Seller> optional = sellerRepository.findById(id);
        if(!optional.isPresent()){
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

        return  sellerDto;
    }

    public List<SellerDto> getAllSeller(Integer pageNo, Integer pageSize, Integer sortBy){
        Pageable paging = (Pageable) PageRequest.of(0, 1, Sort.by("id"));
        Page<Seller> pagedResult = sellerRepository.findAll(paging);

        Iterable<Seller> sellers = sellerRepository.findAll();
        List<SellerDto> sellerDtoList = new ArrayList<>();
        sellers.forEach(sellers1 -> sellerDtoList.add(new SellerDto(sellers1.getId(),sellers1.getEmail(),
                sellers1.getFirstName(),sellers1.getMiddleName(),sellers1.getLastName(),sellers1.getGst(),
                sellers1.getCompanyContact(),sellers1.getCompanyName())));
        return sellerDtoList;
    }


    public SellerDto updateSeller(Integer id, SellerCO sellerCO){
        if (!sellerRepository.findById(id).isPresent()){
            throw new AccountDoesNotExists("Invalid Account Credentials");
        }
        Seller seller = sellerRepository.findById(id).get();
        BeanUtils.copyProperties(sellerCO, seller);
        sellerRepository.save(seller);
        SellerDto sellerDto = getSeller(seller.getId());
        return sellerDto;
    }

    public Map<String,Boolean> deleteSeller(Integer id){
        Map<String,Boolean> map = new HashMap<>();
        Optional<Seller> optional = sellerRepository.findById(id);

        if(!optional.isPresent()){
            map.put("Deleted",false);
        }
        else {
            sellerRepository.deleteById(id);
            map.put("Deleted",true);
        }
        return map;
    }

//    public String updateAddress(Integer id, AddressDto addressDto) {
//        Optional<Address> optional = addressRepository.findById(id);
//        if(!optional.isPresent()){
//            throw new AccountDoesNotExists("Invalid Account Credentials");
//        }
//        Address address = addressRepository.findById(id).get();
//        BeanUtils.copyProperties(addressDto, address);
//        addressRepository.save(address);
//        AddressDto addressDto1 = getAddress(address.getId());
//        return "'Address Updated";
//    }
//    }
}